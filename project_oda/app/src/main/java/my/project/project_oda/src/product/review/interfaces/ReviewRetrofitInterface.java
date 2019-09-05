package my.project.project_oda.src.product.review.interfaces;

import my.project.project_oda.src.product.review.models.ProductReviewResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewRetrofitInterface {

    //후기 조회
    @GET("/productReview")
    Call<ProductReviewResponse> getProductReview(@Query("pNum") int pNum);

}
