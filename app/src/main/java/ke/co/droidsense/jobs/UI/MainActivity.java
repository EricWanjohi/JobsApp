package ke.co.droidsense.jobs.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.Constants.Constants;
import ke.co.droidsense.jobs.R;

public class MainActivity extends AppCompatActivity {
    //Member Variables.
    //Views.
    @BindView( R.id.jobs_list_recyclerView ) RecyclerView jobs_list_recyclerView;

    //Others.
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Initializations.
        ButterKnife.bind( this );

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
        }
        return super.onOptionsItemSelected( item );
    }

    //Handle Transition to Settings Activity.
    private void transitionToSettingsActivity() {
        //Create Explicit Intent.
        Intent settingsActivityIntent = new Intent( MainActivity.this, Settings.class );
        startActivity( settingsActivityIntent );
    }

    //Handle Logout
    private void logout() {
        //SignUut via authenticated user instance.
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent( MainActivity.this, Login.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( intent );
        finish();
    }
}
