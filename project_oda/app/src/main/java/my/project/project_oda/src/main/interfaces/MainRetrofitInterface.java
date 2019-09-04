package my.project.project_oda.src.main.interfaces;

import my.project.project_oda.src.login.models.LoginResponse;
import my.project.project_oda.src.main.models.DirectOrderResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainRetrofitInterface {
    @POST("/directOrder")
    Call<DirectOrderResponse> postDirectOrder(@Body RequestBody params);
}
