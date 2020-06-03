package ke.co.droidsense.jobs.util;

//Item Touch Helper Adapter.
public interface ItemTouchHelperAdapter {
    //Interface Methods
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
