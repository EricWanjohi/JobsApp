package ke.co.droidsense.jobs.Models;

public class Job {
    //Member Variables
    private String job_title;
    private String job_description;
    private int job_budget;
    private String job_due_date;

    //Empty Constructor.
    public Job(){
        //Required by Firebase and Parceler.
    }

    //Constructor.
    public Job(String job_title, String job_description, int job_budget, String job_due_date) {
        this.job_title = job_title;
        this.job_description = job_description;
        this.job_budget = job_budget;
        this.job_due_date = job_due_date;
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

    public int getJob_budget() {
        return job_budget;
    }

    public void setJob_budget(int job_budget) {
        this.job_budget = job_budget;
    }

    public String getJob_due_date() {
        return job_due_date;
    }

    public void setJob_due_date(String job_due_date) {
        this.job_due_date = job_due_date;
    }
}
