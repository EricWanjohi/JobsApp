package ke.co.droidsense.jobs.UI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.Client.DarajaApiClient;
import ke.co.droidsense.jobs.Models.AccessToken;
import ke.co.droidsense.jobs.Models.STKPush;
import ke.co.droidsense.jobs.R;
import ke.co.droidsense.jobs.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static ke.co.droidsense.jobs.Constants.Constants.BUSINESS_SHORT_CODE;
import static ke.co.droidsense.jobs.Constants.Constants.CALLBACKURL;
import static ke.co.droidsense.jobs.Constants.Constants.PARTYB;
import static ke.co.droidsense.jobs.Constants.Constants.PASSKEY;
import static ke.co.droidsense.jobs.Constants.Constants.TRANSACTION_TYPE;

public class LipaNaMpesa extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.etAmount)
    EditText mAmount;
    @BindView(R.id.etPhone)
    EditText mPhone;
    @BindView(R.id.btnPay)
    Button mPay;
    //Member Variables...
    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lipa_na_mpesa );

        ButterKnife.bind( this );

        mProgressDialog = new ProgressDialog( this );
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug( true ); //Set True to enable logging, false to disable.

        mPay.setOnClickListener( this );

        getAccessToken();
    }

    public void getAccessToken() {
        mApiClient.setGetAccessToken( true );
        mApiClient.mpesaService().getAccessToken().enqueue( new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    mApiClient.setAuthToken( response.body().accessToken );
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        } );
    }


    @Override
    public void onClick(View view) {
        if (view == mPay) {
            String phone_number = mPhone.getText().toString();
            String amount = mAmount.getText().toString();
            performSTKPush( phone_number, amount );
        }
    }


    public void performSTKPush(String phone_number, String amount) {
        mProgressDialog.setMessage( "Processing your request" );
        mProgressDialog.setTitle( "Please Wait..." );
        mProgressDialog.setIndeterminate( true );
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword( BUSINESS_SHORT_CODE, PASSKEY, timestamp ),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf( amount ),
                Utils.sanitizePhoneNumber( phone_number ),
                PARTYB,
                Utils.sanitizePhoneNumber( phone_number ),
                CALLBACKURL,
                "MPESA Android Test", //Account reference
                "Testing"  //Transaction description
        );

        mApiClient.setGetAccessToken( false );

        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush( stkPush ).enqueue( new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d( "post submitted to API. %s", response.body() );
                    } else {
                        Timber.e( "Response %s", response.errorBody().string() );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e( t );
            }
        } );
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
