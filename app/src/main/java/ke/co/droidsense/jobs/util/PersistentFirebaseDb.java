package ke.co.droidsense.jobs.util;

import com.google.firebase.database.FirebaseDatabase;

public class PersistentFirebaseDb {
    //Member Variable...
    private static FirebaseDatabase firebaseDatabase;

    //Get Instance...
    public static FirebaseDatabase getFirebaseDatabase() {
        //Check if Instance is null...
        if (firebaseDatabase == null) {
            //Create new Instance...
            firebaseDatabase = FirebaseDatabase.getInstance();
            //Set Persistence to true...
            firebaseDatabase.setPersistenceEnabled( true );
        }
        return firebaseDatabase;
    }
}
