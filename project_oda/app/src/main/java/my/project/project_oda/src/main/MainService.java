package my.project.project_oda.src.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONObject;

import my.project.project_oda.src.ApplicationClass;
import my.project.project_oda.src.main.interfaces.LoginActivityView;
import my.project.project_oda.src.main.interfaces.SignUpActivityView;
import my.project.project_oda.src.main.interfaces.MainRetrofitInterface;
import my.project.project_oda.src.main.models.CheckResponse;
import my.project.project_oda.src.main.models.LoginResponse;
import my.project.project_oda.src.main.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.*;

class MainService {
    private SignUpActivityView mSignUpActivityView;
    private LoginActivityView mLoginUpActivityView;
    private String id;
    private String pw;
    private int business;
    private String address;

    JSONObject params;
    SharedPreferences sf;
    SharedPreferences.Editor editor;

    //생성자
    //아이디 중복확인
    MainService(final SignUpActivityView signUpActivityView, String id) {
        this.mSignUpActivityView = signUpActivityView;
        this.id = id;
    }

    //회원가입
    MainService(final SignUpActivityView signUpActivityView, String id, String pw, int business, String address){
        this.mSignUpActivityView = signUpActivityView;
        this.id = id;
        this.pw = pw;
        this.business = business;
        this.address = address;
    }

    //로그인
    MainService(final LoginActivityView signUpActivityView, String id, String pw){
        this.mLoginUpActivityView = signUpActivityView;
        this.id = id;
        this.pw = pw;
    }

    //아이디 중복확인
    void getTest() {

        final MainRetrofitInterface mainRetrofitInterface = getRetrofit_for_check().create(MainRetrofitInterface.class);
        Log.d(TAG,"id: "+id);
        mainRetrofitInterface.getTest(id).enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                final CheckResponse checkResponse = response.body();
                if (checkResponse == null) {
                    mSignUpActivityView.DuplicateFailure("서버 연결 실패");
                    return;
                }

                mSignUpActivityView.DuplicateSuccess(checkResponse.getCode(),checkResponse.getMessage());
                Log.d(TAG, "중복확인 성공");
            }

            @Override
            public void onFailure(Call<CheckResponse> call, Throwable t) {
                mSignUpActivityView.DuplicateFailure("서버 연결 실패");
                Log.d(TAG, "중복확인 실패");
            }
        });
    }

    //회원가입
    void signUp() {
        try {
            params = new JSONObject();
            params.put("id", id);
            params.put("pw", pw);
            params.put("business", business);
            params.put("address", address);
        } catch (Exception e) {
            Log.d(TAG, "error: "+e);
        }

        final MainRetrofitInterface signUpRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        signUpRetrofitInterface.signUp(RequestBody.create(MEDIA_TYPE_JSON, params.toString())).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.SignUpFailure(null);
                    Log.d(TAG, "회원가입 실패");
                    return;
                }
                String message = "아이디: "+signUpResponse.getId() + ", " + signUpResponse.getMessage();
                mSignUpActivityView.SignUpSuccess(message);
                Log.d(TAG, "회원가입 성공");
                Log.d(TAG, "code: "+signUpResponse.getCode());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.SignUpFailure(null);
                Log.d(TAG, "Failure");
            }
        });
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

        final MainRetrofitInterface LoginRetrofitInterface = getRetrofit_for_check().create(MainRetrofitInterface.class);
        LoginRetrofitInterface.Login(RequestBody.create(MEDIA_TYPE_JSON, params.toString())).enqueue(new Callback<LoginResponse>() {
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
                    mLoginUpActivityView.LoginSuccess(loginResponse.getJwt(),loginResponse.getMessage());
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
