package ke.co.droidsense.jobs.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.droidsense.jobs.R;

public class NewJob extends AppCompatActivity {
    //Member Variables.
    @BindView( R.id.job_title ) TextInputLayout job_title;
    @BindView( R.id.job_description ) TextInputLayout job_description;
    @BindView( R.id.job_budget ) TextInputLayout job_budget;
    @BindView( R.id.add_new_job_button ) Button add_new_job_button;
    @BindView( R.id.add_new_job_header ) TextView add_new_job_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_job );

        //Initializations.
        ButterKnife.bind( this );
    }
}
