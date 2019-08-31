package my.project.project_oda.src.login.interfaces;


import my.project.project_oda.src.login.models.LoginResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {

    //로그인용
    @POST("/user/token")
    Call<LoginResponse> Login(@Body RequestBody params);

}
