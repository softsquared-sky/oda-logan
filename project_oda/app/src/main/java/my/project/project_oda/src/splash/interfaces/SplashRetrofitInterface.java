package my.project.project_oda.src.splash.interfaces;

import my.project.project_oda.src.splash.model.SplashResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SplashRetrofitInterface {

    //token 필요로 하는 api에 전송
    @POST("/productReview")
    Call<SplashResponse> postAutoLogin(@Body RequestBody params);

}
