package my.project.project_oda.src.login;

import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONObject;
import my.project.project_oda.src.login.interfaces.LoginActivityView;
import my.project.project_oda.src.login.interfaces.LoginRetrofitInterface;
import my.project.project_oda.src.login.models.LoginResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.X_ACCESS_TOKEN;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;
import static my.project.project_oda.src.ApplicationClass.sSharedPreferences;

class LoginService {
    private LoginActivityView mLoginUpActivityView;
    private String id;
    private String pw;

    JSONObject params;

    //로그인
    LoginService(final LoginActivityView loginActivityView, String id, String pw){
        this.mLoginUpActivityView = loginActivityView;
        this.id = id;
        this.pw = pw;
    }

    //로그인
    void LogIn(){
        try {
            params = new JSONObject();
            params.put("id", id);
            params.put("pw", pw);
        } catch (Exception e) {
            Log.d(TAG, "error: "+e);
        }

        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.Login(RequestBody.create(MEDIA_TYPE_JSON, params.toString())).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginUpActivityView.LoginFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //로그인 성공
                if(loginResponse.getIsSuccess()) {
                    String jwt = loginResponse.getResult().get("jwt").getAsString();
                    Log.d(TAG, "받아온 jwt값: "+jwt);
                    //로그인시 받아오는 jwt를 sharedpreference에 저장
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(X_ACCESS_TOKEN, jwt);
                    editor.commit();
                    mLoginUpActivityView.LoginSuccess(loginResponse.getMessage());
                //로그인 실패
                }else{
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
