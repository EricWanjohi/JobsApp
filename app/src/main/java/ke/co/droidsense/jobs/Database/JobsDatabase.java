package ke.co.droidsense.jobs.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ke.co.droidsense.jobs.Dao.JobsDao;
import ke.co.droidsense.jobs.Models.Job;
import ke.co.droidsense.jobs.Models.User;

@Database(entities = {Job.class, User.class}, version = 1, exportSchema = false)
public abstract class JobsDatabase extends RoomDatabase {
    //Member Variables...
    private static String Database = "JobsDb";
    private static JobsDatabase INSTANCE;

    //Database Instance Getter...

    public static JobsDatabase getJobsDb(Context context) {
        //Check if Instance is null...
        if (INSTANCE == null) {
            //create new Instance of Db...
            INSTANCE = Room.databaseBuilder( context, JobsDatabase.class, Database )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    //Create Dao to get data from Db...
    public abstract JobsDao getJobsDao();
}
