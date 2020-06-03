package ke.co.droidsense.jobs.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import ke.co.droidsense.jobs.Dao.JobsDao;
import ke.co.droidsense.jobs.Database.JobsDatabase;
import ke.co.droidsense.jobs.Models.Job;

public class JobsRepository {
    //Member Variables,,,
    private static JobsRepository jobsRepository;
    private JobsDatabase jobsDatabase;
    private JobsDao jobsDao;
    private LiveData<Job> jobsLiveData;

    //Constructor...
    private JobsRepository(Context context) {
        jobsDatabase = JobsDatabase.getJobsDb( context );
        jobsDao = jobsDatabase.getJobsDao();
        jobsLiveData = jobsDao.getAllJobs();
    }

    //Repository Instance Getter...
    public static JobsRepository getJobsRepository(Context context) {
        //Check if Instance is null...
        if (jobsRepository == null) {
            jobsRepository = new JobsRepository( context );
        }
        return jobsRepository;
    }

    //Get Jobs LiveData Object
    public LiveData<Job> getJobsLiveData() {
        //Return LiveData object.
        return jobsLiveData;
    }
}
