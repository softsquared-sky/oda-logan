package my.project.project_oda.src.cart.interfaces;


import my.project.project_oda.src.cart.models.CartResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CartRetrofitInterface {

    //로그인용
    @GET("/basket")
    Call<CartResponse> getBasket();

}
