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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.Constants.Constants;
import ke.co.droidsense.jobs.Models.User;
import ke.co.droidsense.jobs.R;

public class Register extends AppCompatActivity implements View.OnClickListener{
    //Member Variables.
    //Views.
    @BindView( R.id.register_header ) TextView registration_header;
    @BindView( R.id.registration_email ) TextInputLayout registration_email;
    @BindView( R.id.registration_full_name ) TextInputLayout registration_name;
    @BindView( R.id.registration_phone ) TextInputLayout registration_phone;
    @BindView( R.id.registration_password ) TextInputLayout registration_password;
    @BindView( R.id.registration_confirm_password ) TextInputLayout registration_confirm_password;
    @BindView( R.id.registration_button ) Button registration_button;
    @BindView( R.id.login_textView ) TextView login_textView;

    //String values.
    private String email;
    private String phone;
    private String password;
    private String confirm_password;

    //Others.
    FirebaseDatabase firebaseDatabase;
    DatabaseReference UserReference;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    //Booleans
    private boolean isvaliEmailAddress;
    private boolean isValidPasswordMatch;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        //Initializations.
        ButterKnife.bind( this );

        //Create AuthStateListener.
        createAuthStateListener();

        //Get Database and reference.
        getDatabaseAndReference();

        //Set click listeners.
        registration_button.setOnClickListener( this );
        login_textView.setOnClickListener( this );

    }

    //Create Auth State Listener.
    private void createAuthStateListener() {
        //Create New AuthStateListener.
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Get User.
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                //Check if user is null.
                if (firebaseUser != null){
                    //Create Intent to transition to Main Activity
                    Intent mainActivityIntent = new Intent( Register.this, MainActivity.class );
                    mainActivityIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity( mainActivityIntent );
                    finish();
                }
            }
        };
    }

    //Validation process.
    private void validateUserInputs(String email, String password, String confirm_password) {
        //Check and validate user input boolean
        isvaliEmailAddress = validateEmail(email);
        isValidPasswordMatch = validatePasswords(password, confirm_password);
    }

    private boolean validatePasswords(String password, String confirm_password) {
        //Check if password is same as c.password and length is minimum 6 characters.
        if (password.length() < 6){
            //set Error message.
            registration_password.setError( "Password is less than 6 characters" );
            registration_password.setFocusable( true );
            return false;
        } else if (!password.equals( confirm_password )){
            //Set Error.
            registration_confirm_password.setError( "Passwords do not match" );
            registration_confirm_password.setFocusable( true );
            return false;
        }
        return true;
    }

    //CHeck that user email is valid
    private boolean validateEmail(String email) {
        //Create a boolean.
        boolean isValidEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher( email ).matches());
        //If statement
        if (!isValidEmail){
            registration_email.setError( "Use a valid email address" );
            registration_email.setFocusable( true );
            return false;
        }
        return isValidEmail;
    }

    //Create new User
    private void createNewUser() {
        //Get String values of inputs.
        getStringValues();

        //Validate Inputs
        validateUserInputs(email, password, confirm_password);

        //Cross Check.
        if (!isvaliEmailAddress || !isValidPasswordMatch) return;

        //Create New User object.
        User user = new User(name, email, phone, password, confirm_password );

        //Push User object to Firebase database.
        UserReference.child( phone ).push().setValue( user );

        //Create user with Email and Password.
        firebaseAuth.createUserWithEmailAndPassword( email, password )
                .addOnCompleteListener( Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Check if task is successful.
                        if (task.isSuccessful()){
                            //Toast to user
                            Toast.makeText( Register.this, "Registration successful.", Toast.LENGTH_SHORT ).show();
                        } else {
                            //Toast.
                            Toast.makeText( Register.this, "Registration failed.", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
    }

    //Get input value strings
    private void getStringValues() {
        //Create string input holders.
        name = registration_name.getEditText().getText().toString().trim();
        email = registration_email.getEditText().getText().toString().trim();
        phone = registration_phone.getEditText().getText().toString().trim();
        password = registration_password.getEditText().getText().toString().trim();
        confirm_password = registration_confirm_password.getEditText().getText().toString().trim();
    }

    //Get Application database, auth and reference.
    private void getDatabaseAndReference() {
        //Initialize db and reference.
        firebaseDatabase = FirebaseDatabase.getInstance();
        UserReference = firebaseDatabase.getReference( Constants.Users );
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //OnStart
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener( authStateListener );
    }

    //OnStop Override.
    @Override
    protected void onStop() {
        super.onStop();

        //Check if authStateListener is null.
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener( authStateListener );
        }
    }

    @Override
    public void onClick(View view) {
        //Check view clicked.
        switch (view.getId()){
            //Case Register button.
            case R.id.registration_button:
                createNewUser();
                break;

            //Case Login Text.
            case R.id.login_textView:
                //Transition to Login Activity
                createTransitionToMainActivity();
                break;
        }
    }

    //Transitioning to MainActivity.
    private void createTransitionToMainActivity() {
        //Create Explicit intent.
        Intent mainActivityTransitionIntent = new Intent( Register.this, MainActivity.class );
        startActivity( mainActivityTransitionIntent );
    }
}
