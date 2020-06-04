package ke.co.droidsense.jobs.Interceptor;

import android.util.Base64;

import java.io.IOException;

import ke.co.droidsense.jobs.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

    //Constructor...
    public AccessTokenInterceptor() {
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        String keys = BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET;

        Request request = chain.request().newBuilder()
                .addHeader( "Authorization", "Basic " + Base64.encodeToString( keys.getBytes(), Base64.NO_WRAP ) )
                .build();
        return chain.proceed( request );
    }
}
