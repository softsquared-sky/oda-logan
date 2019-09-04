package my.project.project_oda.src.product.interfaces;

import my.project.project_oda.src.product.models.ProductDetailResponse;
import my.project.project_oda.src.product.models.ProductReviewResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {

    //상품상세
    @GET("/productDetail")
    Call<ProductDetailResponse> getProductDetail(@Query("pNum") int pNum );

    //후기 조회
    @GET("/productReview")
    Call<ProductReviewResponse> getProductReview(@Query("pNum") int pNum);

}
