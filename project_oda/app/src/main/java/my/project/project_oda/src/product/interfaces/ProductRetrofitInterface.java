package my.project.project_oda.src.product.interfaces;

import my.project.project_oda.src.product.detail.models.ProductDetailResponse;
import my.project.project_oda.src.product.models.BasketResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {

    //상품상세
    @GET("/product")
    Call<ProductDetailResponse> getProductDetail(@Query("pNum") int pNum );

    @POST("/basket")
    Call<BasketResponse> postBasket(@Body RequestBody params);
}
