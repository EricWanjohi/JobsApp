package ke.co.droidsense.jobs.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ke.co.droidsense.jobs.Models.Job;
import ke.co.droidsense.jobs.Repository.JobsRepository;

public class JobsViewModel extends ViewModel {

    //Member Variables...
    private LiveData<Job> jobsLiveData;
    private JobsRepository jobsRepository;
    private Application application;

    //Constructor...
    public JobsViewModel(Application application) {
        this.application = application;

        //Initialize ViewModel...
        initializeViewModel();
    }

    //Init Components...
    private void initializeViewModel() {
        //Init...
        jobsRepository = JobsRepository.getJobsRepository( application );
        jobsLiveData = jobsRepository.getJobsLiveData();
    }

    //LiveData Object Getter...
    public LiveData<Job> getJobsLiveData() {
        return jobsLiveData;
    }
}
