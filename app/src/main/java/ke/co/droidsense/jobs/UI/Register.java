package ke.co.droidsense.jobs.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.R;

public class Register extends AppCompatActivity {
    //Member Variables.
    //Views.
    @BindView( R.id.register_header ) TextView registration_header;
    @BindView( R.id.registration_email ) TextInputLayout registration_email;
    @BindView( R.id.registration_phone ) TextInputLayout registration_phone;
    @BindView( R.id.registration_password ) TextInputLayout registration_password;
    @BindView( R.id.registration_confirm_password ) TextInputLayout registration_confirm_password;
    @BindView( R.id.registration_button ) Button registration_button;
    @BindView( R.id.login_textView ) TextView login_textview;

    //Others.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        //Initializations.
        ButterKnife.bind( this );


    }
}
