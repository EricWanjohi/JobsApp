package ke.co.droidsense.jobs.util;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import ke.co.droidsense.jobs.Adapters.JobsViewHolder;
import ke.co.droidsense.jobs.Models.Job;

public class JobsFirebaseAdapter extends FirebaseRecyclerAdapter<Job, JobsViewHolder> implements ItemTouchHelperAdapter {
    //Member Variables.
    private DatabaseReference jobsReference;
    private Context context;
    private OnStartDragListener onStartDragListener;



    //Constructor.
    public JobsFirebaseAdapter(@NonNull FirebaseRecyclerOptions<Job> options) {
        super( options );
    }

    //onBindViewHolder.
    @Override
    protected void onBindViewHolder(@NonNull JobsViewHolder holder, int position, @NonNull Job model) {

    }

    //onCreateViewHolder.
    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    //On Item Move implementation.
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    //OnItemDismiss implementation.
    @Override
    public void onItemDismiss(int position) {

    }
}
