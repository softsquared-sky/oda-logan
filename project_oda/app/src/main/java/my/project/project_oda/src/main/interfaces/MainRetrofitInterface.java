package my.project.project_oda.src.main.interfaces;

import my.project.project_oda.src.main.models.CheckResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    //아이디 중복확인용
    @GET("/id")
    Call<CheckResponse> getTest(@Query("id") final String id);

    //회원가입용
    @POST("/user")
    Call<CheckResponse> getTestPathAndQuery(
            @Query("id") final String id,
            @Query("pw") final String password,
            @Query("business") final String business,
            @Query("address") final String address
    );

    @POST("/test")
    Call<CheckResponse> postTest(@Body RequestBody params);
}
