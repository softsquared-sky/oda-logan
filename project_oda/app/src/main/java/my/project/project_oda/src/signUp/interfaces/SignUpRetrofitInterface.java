package my.project.project_oda.src.signUp.interfaces;

import my.project.project_oda.src.signUp.models.CheckResponse;
import my.project.project_oda.src.signUp.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {

    //아이디 중복확인용
    @GET("/id")
    Call<CheckResponse> getDuplicate(@Query("id") String id);

    //회원가입용
    @POST("/user")
    Call<SignUpResponse> postSignUp(@Body RequestBody params);

}
