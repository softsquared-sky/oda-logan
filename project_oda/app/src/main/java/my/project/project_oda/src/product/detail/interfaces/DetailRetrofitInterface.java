package my.project.project_oda.src.product.detail.interfaces;

import my.project.project_oda.src.product.detail.models.ProductDetailResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailRetrofitInterface {

    //상품상세
    @GET("/productDetail")
    Call<ProductDetailResponse> getProductDetail(@Query("pNum") int pNum);

}
