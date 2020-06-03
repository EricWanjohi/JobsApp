package ke.co.droidsense.jobs.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.Adapters.JobsViewHolder;
import ke.co.droidsense.jobs.Constants.Constants;
import ke.co.droidsense.jobs.Models.Job;
import ke.co.droidsense.jobs.R;
import ke.co.droidsense.jobs.util.PersistentFirebaseDb;

public class JobsList extends AppCompatActivity {
    //Member Variables.
    //Views.
    @BindView( R.id.jobs_list_recyclerView ) RecyclerView jobs_list_recyclerView;

    //Others.
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference jobsListReference;
    private FirebaseRecyclerOptions<Job> options;
    private FirebaseRecyclerAdapter<Job, JobsViewHolder> adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Initializations.
        ButterKnife.bind( this );

        //Get database reference.
        getDatabaseReference();

        //Get Jobs from Firebase
        getJobsFromDatabase();
    }

    //Init ProgressDialog.
    private void createProgressDialog() {
        //Init.
        progressDialog = new ProgressDialog( this );
        progressDialog.setMessage( "Loading new jobs..." );
        progressDialog.show();
    }

    //Fetch job items from DB.
    private void getJobsFromDatabase() {

        //Create new Instance of FirebaseRecyclerOptions.
        options = new FirebaseRecyclerOptions.Builder<Job>()
                .setQuery( jobsListReference, Job.class )
                .build();

        //Init Adapter
        adapter = new FirebaseRecyclerAdapter<Job, JobsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull JobsViewHolder holder, int position, @NonNull Job model) {
                //Bind data to holder item
                holder.bindJobItems( model );
            }

            @NonNull
            @Override
            public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //Create View Object
                View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.job_item_layout, parent, false );

                return new JobsViewHolder(view);
            }
        };

        //Set RecyclerView Layout Manager and Adapter.
        jobs_list_recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        jobs_list_recyclerView.setAdapter( adapter );
        jobs_list_recyclerView.setHasFixedSize( true );
        adapter.notifyDataSetChanged();
    }

    //Get Reference
    private void getDatabaseReference() {
        //Get Instance.
        firebaseDatabase = PersistentFirebaseDb.getFirebaseDatabase();
        firebaseAuth = FirebaseAuth.getInstance();
        jobsListReference = firebaseDatabase.getReference(Constants.NEW_JOBS);
    }

    //OnCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Get Menu inflater
        MenuInflater menuInflater = getMenuInflater();

        //Inflate menu
        menuInflater.inflate( R.menu.main_menu, menu );

        //Initialize SharedPreferences.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );
        editor = sharedPreferences.edit();

        //Get menu item
        MenuItem menuItem = menu.findItem( R.id.searchView );
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint( "Search by title" );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Create method to add query to SharedPreferences.
                addToSharedPreferences(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }

    //Add Query item to Shared preferences
    private void addToSharedPreferences(String query) {
        //Use Editor to edit preferences.
        editor.putString( Constants.SEARCHED_JOBS_QUERY, query ).apply();
    }

    //OnOptionsItemSelected.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Get item id.
        int viewId = item.getItemId();

        //Create switch to handle clicks on items.
        switch (viewId){
            //Case Logout.
            case R.id.logout:
                //Logout
                logout();
                break;

            //Case Settings
            case R.id.settings_button:
                //Transition to settings
                transitionToSettingsActivity();
                break;

            //Case Add New Job.
            case R.id.add_job:
                //Transition to NewJob Activity
                Intent newJobIntent = new Intent( JobsList.this, NewJob.class );
                startActivity( newJobIntent );
                break;
        }
        return super.onOptionsItemSelected( item );
    }

    //Handle Transition to Settings Activity.
    private void transitionToSettingsActivity() {
        //Create Explicit Intent.
        Intent settingsActivityIntent = new Intent( JobsList.this, Settings.class );
        startActivity( settingsActivityIntent );
    }

    //Handle Logout
    private void logout() {
        //SignUut via authenticated user instance.
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent( JobsList.this, Login.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( intent );
        finish();
    }

    //Handle Listeners in onStart and onStop.
    //OnStart.
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    //onStop.
    @Override
    protected void onStop() {
        super.onStop();
        //Check if adapter is null
        if (adapter != null){
            //Stop Listening.
            adapter.stopListening();
        }
    }

}
