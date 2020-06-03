package ke.co.droidsense.jobs.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //Views.
    @BindView( R.id.email_input ) TextInputLayout email_input;
    @BindView( R.id.password_input ) TextInputLayout password_input;
    @BindView( R.id.login_header ) TextView login_header;
    @BindView( R.id.login_button ) Button login_button;
    @BindView( R.id.register_textView_text ) TextView register_textView;

    //FirebaseAuth.
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    //String Values.
    private String email;
    private String password;

    //Booleans.
    private boolean validPassword;
    private boolean validEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        //Initializations.
        ButterKnife.bind( this );

        //AuthStateListener
        getAuthStateListener();

        //Set Click Listeners.
        register_textView.setOnClickListener( this );
        login_button.setOnClickListener( this );
    }

    //Get Strings
    private void getUserStrings() {
        email = email_input.getEditText().getText().toString().trim();
        password = password_input.getEditText().getText().toString().trim();
    }

    //Validate Email.
    private boolean validateEmail(String email){
        boolean isValidEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher( email ).matches());
        //check if isValidEmail.
        if (!isValidEmail){
            //Set Error.
            email_input.setError( "Email is not valid" );
            email_input.setFocusable( true );
            return false;
        }
        return isValidEmail;
    }

    //Validate Password.
    private boolean validatePassWord(String password){
        boolean isValidPassword = (password != null && !password.equals( "" ));
        //Check password.
        if (isValidPassword && password.length() < 6){
            //Set Error.
            password_input.setError( "Password less than 6 characters" );
            password_input.setFocusable( true );
            return false;
        }
        return isValidPassword;
    }

    //Get AuthStateListener
    private void getAuthStateListener() {
        //Get Auth instance.
        firebaseAuth = FirebaseAuth.getInstance();

        //AuthStateListener.
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Create User object.
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                //Check if user is null.
                if (firebaseUser != null){
                    //Create new Intent
                    Intent loginIntent = new Intent( Login.this, JobsList.class );
                    loginIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity( loginIntent );
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        //Check view clicked
        switch (view.getId()){
            //Case Login Button.
            case R.id.login_button:
                loginUser();
                break;

            //Case Register Text.
            case R.id.register_textView_text:
                transitionToRegisterActivity();
                break;
        }
    }

    //OnStart
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener( authStateListener );
    }

    //OnStop
    @Override
    protected void onStop() {
        super.onStop();
        //Check authStateListener
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener( authStateListener );
        }
    }

    //Login user
    private void loginUser() {
        //Get user input String
        getUserStrings();

        //Get Validation.
//        validateInputs( email, password );
        validEmail = validateEmail( email );
        validPassword = validatePassWord( password );

        //Check validity.
        if (!validEmail || !validPassword) return;

        //Sign in with email and password
        firebaseAuth.signInWithEmailAndPassword( email, password )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Check if task is successful.
                        if (task.isSuccessful()){
                            //Toast.
                            Toast.makeText( Login.this, "Logging in as " + email, Toast.LENGTH_SHORT ).show();
                        } else {
                            //Toast.
                            Toast.makeText( Login.this, "Authentication failed.", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );

        //Toast for now.
        Toast.makeText( this, "Logging in as " + email_input.getEditText().getText().toString().trim(), Toast.LENGTH_SHORT ).show();
    }

    //transition To Register Activity
    private void transitionToRegisterActivity() {
        //Create Explicit intent.
        Intent registerActivityIntent = new Intent( Login.this, Register.class );
        startActivity( registerActivityIntent );
    }
}
