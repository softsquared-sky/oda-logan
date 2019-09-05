package my.project.project_oda.src.splash;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import my.project.project_oda.R;
import my.project.project_oda.src.splash.interfaces.SplashActivityView;
import my.project.project_oda.src.splash.interfaces.SplashRetrofitInterface;
import my.project.project_oda.src.splash.model.SplashResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class splashService {

    Context mContext;
    private SplashActivityView mSplashActivityView;
    private JSONObject mParams;

    splashService(final SplashActivityView mSplashActivityView, JSONObject mParams, Context mContext) {
        this.mSplashActivityView = mSplashActivityView;
        this.mParams = mParams;
        this.mContext = mContext;
    }

    void postAutoLogin() {

        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.postAutoLogin(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {

                final SplashResponse splashResponse = response.body();
                if (splashResponse == null) {
                    mSplashActivityView.postAutoLoginFailure(mContext.getString(R.string.network_no_response));
                    return;
                }

                mSplashActivityView.postAutoLoginSuccess(splashResponse.getCode());
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                mSplashActivityView.postAutoLoginFailure(mContext.getString(R.string.network_fail));
            }
        });
    }

}
