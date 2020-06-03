package ke.co.droidsense.jobs.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import ke.co.droidsense.jobs.Models.Job;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface JobsDao {
    //CRUD functions.
    //Query all Jobs...
    @Query("SELECT * FROM Jobs")
    LiveData<Job> getAllJobs();

    //Insert Job Item...
    @Insert
    void addNewJob(Job job);

    //Update Job Item...
    @Update(onConflict = REPLACE)
    void updateJob(Job job);

    //Delete Job Item...
    @Delete
    void deleteJob(Job job);
}
