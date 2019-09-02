package my.project.project_oda.src.signup;

import android.util.Log;

import org.json.JSONObject;
import my.project.project_oda.R;
import my.project.project_oda.src.signup.interfaces.SignUpActivityView;
import my.project.project_oda.src.signup.interfaces.SignUpRetrofitInterface;
import my.project.project_oda.src.signup.models.CheckResponse;
import my.project.project_oda.src.signup.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.*;

class SignUpService {
    private SignUpActivityView mSignUpActivityView;
    private String mId;
    private JSONObject mParams;

    //생성자
    //아이디 중복확인
    SignUpService(final SignUpActivityView signUpActivityView, String id) {
        this.mSignUpActivityView = signUpActivityView;
        this.mId = id;
    }

    //회원가입
    SignUpService(final SignUpActivityView signUpActivityView, JSONObject params) {
        this.mSignUpActivityView = signUpActivityView;
        this.mParams = params;
    }

    //아이디 중복확인
    void getDuplicate() {

        final SignUpRetrofitInterface mainRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        Log.d(TAG, "id: " + mId);
        mainRetrofitInterface.getDuplicate(mId).enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                final CheckResponse checkResponse = response.body();
                if (checkResponse == null) {
                    mSignUpActivityView.DuplicateFailure("중복확인 응답 없음");
                    return;
                }

                mSignUpActivityView.DuplicateSuccess(checkResponse.getCode(), checkResponse.getMessage());
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
    void postSignUp() {
        try {
            Log.d(TAG, "id: "+mParams.get("id"));
        }catch (Exception e){

        }
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.postSignUp(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response == null){

                    return;
                }
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.SignUpFailure(null);
                    //Log.d(TAG, "회원가입 실패");
                    return;
                }

                String message = signUpResponse.getMessage();
                mSignUpActivityView.SignUpSuccess(signUpResponse.getCode(), message);
                //Log.d(TAG, "code: " + signUpResponse.getCode());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.SignUpFailure(null);
                Log.d(TAG, "Failure");
            }
        });
    }
}
