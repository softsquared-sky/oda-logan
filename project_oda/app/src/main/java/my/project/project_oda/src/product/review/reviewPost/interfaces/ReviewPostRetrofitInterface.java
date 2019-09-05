package my.project.project_oda.src.product.review.reviewPost.interfaces;

import my.project.project_oda.src.product.review.reviewPost.models.ProductPostReviewResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReviewPostRetrofitInterface {

    //후기 등록
    @POST("/productReview")
    Call<ProductPostReviewResponse> postProductReview(@Body RequestBody params);

}
