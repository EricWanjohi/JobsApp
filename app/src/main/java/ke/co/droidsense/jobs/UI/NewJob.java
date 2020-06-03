package ke.co.droidsense.jobs.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.Constants.Constants;
import ke.co.droidsense.jobs.Models.Job;
import ke.co.droidsense.jobs.R;
import ke.co.droidsense.jobs.util.PersistentFirebaseDb;

public class NewJob extends AppCompatActivity implements View.OnClickListener {
    //Member Variables.
    @BindView( R.id.job_title ) TextInputLayout job_title;
    @BindView( R.id.job_description ) TextInputLayout job_description;
    @BindView( R.id.job_budget ) TextInputLayout job_budget;
    @BindView( R.id.add_new_job_button ) Button add_new_job_button;
    @BindView( R.id.add_new_job_header ) TextView add_new_job_header;

    //String values
    String jobTitle;
    String jobDesc;
    String jobBudget;

    //Others.
    private DatabaseReference jobsReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private boolean isValidJobTitleInput;
    private boolean isValidJobDescInput;
    private boolean isValidJobBudgetInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_job );

        //Initializations.
        ButterKnife.bind( this );

        //Get database reference.
        getDatabaseReference();

        //Set Click Listener on button.
        add_new_job_button.setOnClickListener( this );
    }

    //Handle click events
    @Override
    public void onClick(View view) {
        //Handle clicked view item.
        switch (view.getId()){
            //Case add new button clicked
            case R.id.add_new_job_button:
                collectAndPushJobToFirebase();
                break;
        }
    }

    //Collect input and check validity then push remote and transition.
    private void collectAndPushJobToFirebase() {

        //Collect job data.
        jobTitle = job_title.getEditText().getText().toString().trim();
        jobDesc = job_description.getEditText().getText().toString().trim();
        jobBudget = job_budget.getEditText().getText().toString().trim();

        //Valid inputs.
        boolean validJobTitle = isValidJobTitle( jobTitle );
        boolean validJobDesc = isValidJobDesc( jobDesc );
        boolean validJobBudget = isValidJobBudget( jobBudget );

        //Validate data inputs.
        if (!validJobTitle || !validJobDesc || !validJobBudget) return;

        //Push Job to FireBase.
        pushJobItemToFirebase();

        //Transition to JobsList.
        transitionToJobsList();
    }

    //Transition to JobsList.
    private void transitionToJobsList() {
        //Create Explicit intent.
        Intent transitionIntent = new Intent( NewJob.this, JobsList.class );
        transitionIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( transitionIntent );
    }

    //Save Job Item to Database.
    private void pushJobItemToFirebase() {
        //Push Job item to firebase
        jobsReference.push().setValue( new Job( jobTitle, jobDesc, jobBudget ));
    }

    //Get Reference
    private void getDatabaseReference() {
        firebaseDatabase = PersistentFirebaseDb.getFirebaseDatabase();
        jobsReference = firebaseDatabase.getReference( Constants.NEW_JOBS);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //Checking JobBudget.
    private boolean isValidJobBudget(String jobBudget) {
        //Boolean Checks.
        isValidJobBudgetInput = (jobBudget != null && !jobBudget.equals( "" ) && !jobBudget.isEmpty());

        //Check jobBudget is not null or empty.
        if(!isValidJobBudgetInput){
            //Set Error.
            job_budget.setError( "Cannot be empty" );
            job_budget.setFocusable( true );
        }
        return isValidJobBudgetInput;
    }

    //Checking JobTitle
    private boolean isValidJobTitle(String jobTitle){
        isValidJobTitleInput = (jobTitle != null && !jobTitle.equals( "" ) && !jobTitle.isEmpty());

        //check input is not null
        if (!isValidJobTitleInput){
            //Set Error.
            job_title.setError( "Cannot be empty" );
            job_title.setFocusable( true );
        }
        return isValidJobTitleInput;
    }

    //Checking JobDescription
    private boolean isValidJobDesc(String jobDesc){
        isValidJobDescInput = (jobDesc != null && !jobDesc.equals( "" ) && !jobDesc.isEmpty());

        //Check JobDesc is not null or empty.
        if(!isValidJobDescInput){
            //Set Error.
            job_description.setError( "Cannot be empty" );
            job_description.setFocusable( true );
        }
        return isValidJobDescInput;
    }
}
