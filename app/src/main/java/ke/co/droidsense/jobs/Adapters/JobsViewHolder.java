package ke.co.droidsense.jobs.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ke.co.droidsense.jobs.Models.Job;
import ke.co.droidsense.jobs.R;

public class JobsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Member Variables.
    private View view;
    private Context context;

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

        //Set Text on view Items
        jobTitle.setText( job.getJob_title() );
        jobDesc.setText( job.getJob_description() );
        jobBudget.setText("Budget (Ksh) " + job.getJob_budget() );
    }

    //Handle Click Events.
    @Override
    public void onClick(View clickedView) {

    }
}
