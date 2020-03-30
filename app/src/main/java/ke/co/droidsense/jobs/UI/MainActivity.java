package ke.co.droidsense.jobs.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.R;

public class MainActivity extends AppCompatActivity {
    //Member Variables.
    //Views.
    @BindView( R.id.jobs_list_recyclerView )
    RecyclerView jobs_list_recyclerView;

    //Others.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Initializations.
        ButterKnife.bind( this );

    }
}
