package my.project.project_oda.src.login;

import android.util.Log;

import org.json.JSONObject;

import my.project.project_oda.src.login.interfaces.LoginActivityView;
import my.project.project_oda.src.login.interfaces.LoginRetrofitInterface;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import  my.project.project_oda.src.login.models.LoginResponse;
import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class LoginService {
    private LoginActivityView mLoginUpActivityView;
    private String mId;
    private String mPw;

    JSONObject mParams;

    //로그인
    LoginService(final LoginActivityView loginActivityView, String id, String pw) {
        this.mLoginUpActivityView = loginActivityView;
        this.mId = id;
        this.mPw = pw;
    }

    //로그인
    void LogIn() {
        try {
            mParams = new JSONObject();
            mParams.put("id", mId);
            mParams.put("pw", mPw);
        } catch (Exception e) {
            //Log.d(TAG, "error: " + e);
            return;
        }

        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.Login(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response == null) {
                    mLoginUpActivityView.LoginFailure("fail");
                    return;
                }

                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginUpActivityView.LoginFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //로그인 성공
                if (loginResponse.getIsSuccess()) {
                    String jwt = loginResponse.getResult().get("jwt").getAsString();
                    mLoginUpActivityView.LoginSuccess(loginResponse.getMessage(), jwt);
                    //로그인 실패
                } else {
                    mLoginUpActivityView.LoginFailure(loginResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginUpActivityView.LoginFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }
}
