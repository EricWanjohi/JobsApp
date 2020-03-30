package ke.co.droidsense.jobs.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.R;

public class Login extends AppCompatActivity {
    //Member Variables.
    //Views.
    @BindView( R.id.email_input ) TextInputLayout email_input;
    @BindView( R.id.password_input ) TextInputLayout password_input;
    @BindView( R.id.login_header ) TextView login_header;
    @BindView( R.id.login_button ) Button login_button;
    @BindView( R.id.register_textView_text ) TextView register_textView_text;

    //Others


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        //Initializations.
        ButterKnife.bind( this );
    }
}
