package my.project.project_oda.src.signup.interfaces;

import my.project.project_oda.src.signup.models.CheckResponse;
import my.project.project_oda.src.signup.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {

    //아이디 중복확인용
    @GET("/id")
    Call<CheckResponse> getTest(@Query("id") String id);

    //회원가입용
    @POST("/user")
    Call<SignUpResponse> signUp(@Body RequestBody params);

}
