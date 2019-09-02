package my.project.project_oda.config;

import android.util.Log;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import static my.project.project_oda.src.ApplicationClass.X_ACCESS_TOKEN;
import static my.project.project_oda.src.ApplicationClass.*;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        if (jwtToken != null) {
            //Log.d(TAG,"헤더에 붙여지는 jwtToken값: " + jwtToken);
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
        }
        return chain.proceed(builder.build());
    }
}
