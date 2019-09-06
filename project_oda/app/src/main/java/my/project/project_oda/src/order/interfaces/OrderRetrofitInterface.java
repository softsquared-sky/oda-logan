package my.project.project_oda.src.order.interfaces;


import my.project.project_oda.src.login.models.LoginResponse;
import my.project.project_oda.src.order.models.OrderResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderRetrofitInterface {

    //로그인용
    @POST("/payment")
    Call<OrderResponse> postOrderRequest(@Body RequestBody params);

}
