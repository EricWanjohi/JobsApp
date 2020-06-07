package ke.co.droidsense.jobs.Constants;

import ke.co.droidsense.jobs.BuildConfig;

public class Constants {
    //Member variables.
    public static final String Users = "Users";
    public static final String JobsPosted = "Posted Jobs";
    public static final String JobsApplied = "Applied Jobs";
    public static final String SEARCHED_JOBS_QUERY = "";
    public static final String NEW_JOBS = "Jobs List";
    public static final int CONNECT_TIMEOUT = 60 * 1000;  //This is essentially 60 SECONDS...
    public static final int READ_TIMEOUT = 60 * 1000;
    public static final int WRITE_TIMEOUT = 60 * 1000;
    public static final String BASE_URL = "https://sandbox.safaricom.co.ke/";
    public static final String BUSINESS_SHORT_CODE = "174379";
    public static final String PASSKEY = BuildConfig.PASS_KEY;
    public static final String TRANSACTION_TYPE = "CustomerPayBillOnline";
    public static final String PARTYB = "174379"; //same as business shortcode above
    public static final String CALLBACKURL = "https://mpesa-requestbin.herokuapp.com/1c27tgd1";
}
