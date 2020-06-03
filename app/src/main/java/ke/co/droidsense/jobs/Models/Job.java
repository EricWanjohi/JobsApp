package ke.co.droidsense.jobs.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Jobs")
public class Job {
    //Member Variables
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int job_id;
    private String job_title;
    private String job_description;
    private String job_budget;
    private String job_due_date;
    private String index;

    //Empty Constructor.
    @Ignore
    public Job(){
        //Required by Firebase and Parceler.
    }

    //Constructor.
    public Job(String job_title, String job_description, String job_budget) {
//        this.job_id = job_id;
        this.job_title = job_title;
        this.job_description = job_description;
        this.job_budget = job_budget;
//        this.job_due_date = job_due_date;
        this.index = "un_defined";
    }

    //Getters and Setters.
    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_budget() {
        return job_budget;
    }

    public void setJob_budget(String job_budget) {
        this.job_budget = job_budget;
    }

    public String getJob_due_date() {
        return job_due_date;
    }

    public void setJob_due_date(String job_due_date) {
        this.job_due_date = job_due_date;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
