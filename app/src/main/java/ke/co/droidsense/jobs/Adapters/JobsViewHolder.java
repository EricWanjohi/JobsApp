package ke.co.droidsense.jobs.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.dmoral.toasty.Toasty;
import ke.co.droidsense.jobs.Models.Job;
import ke.co.droidsense.jobs.R;

public class JobsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Member Variables.
    private View view;
    private Context context;
    private Button bidButton;

    //Job ViewHolder
    public JobsViewHolder(@NonNull View itemView) {
        super( itemView );

        //Initialize member variables.
        this.view = itemView;
        this.context = itemView.getContext();

        //Set OnclickListeners.
        itemView.setOnClickListener( this );
    }

    public void bindJobItems(Job job){
        //Get View items.
        TextView jobTitle = view.findViewById( R.id.job_title_text );
        TextView jobDesc = view.findViewById( R.id.job_desc_text );
        TextView jobBudget = view.findViewById( R.id.job_budget_text );
        bidButton = view.findViewById( R.id.new_job_button );

        //Set Text on view Items
        jobTitle.setText( job.getJob_title() );
        jobDesc.setText( job.getJob_description() );
        jobBudget.setText("Budget (Ksh) " + job.getJob_budget() );
        bidButton.setOnClickListener( this );
    }

    //Handle Click Events.
    @Override
    public void onClick(View clickedView) {
        //Check view clicked.
        if (clickedView.getId() == R.id.new_job_button){
            //Toast item for now
            Toasty.info( context, "Item clicked..." );
        }
        Toasty.normal( context, "Item View Clicked" );

    }
}
