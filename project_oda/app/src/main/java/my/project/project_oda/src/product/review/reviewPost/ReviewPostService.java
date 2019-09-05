package my.project.project_oda.src.product.review.reviewPost;

import android.util.Log;
import org.json.JSONObject;
import my.project.project_oda.src.product.review.reviewPost.models.ProductPostReviewResponse;
import my.project.project_oda.src.product.review.reviewPost.interfaces.ReviewPostActivityView;
import my.project.project_oda.src.product.review.reviewPost.interfaces.ReviewPostRetrofitInterface;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class ReviewPostService {

    private ReviewPostActivityView mReviewPostActivityView;
    private JSONObject mParams;

    ReviewPostService(final ReviewPostActivityView mReviewPostActivityView, JSONObject params) {
        this.mReviewPostActivityView = mReviewPostActivityView;
        this.mParams = params;
    }

    //상품 후기 등록
    void postProductReview(){

        final ReviewPostRetrofitInterface reviewPostRetrofitInterface = getRetrofit().create(ReviewPostRetrofitInterface.class);
        reviewPostRetrofitInterface.postProductReview(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<ProductPostReviewResponse>() {
            @Override
            public void onResponse(Call<ProductPostReviewResponse> call, Response<ProductPostReviewResponse> response) {
                final ProductPostReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mReviewPostActivityView.postReviewFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //등록 성공
                if (reviewResponse.getIsSuccess()) {
                    mReviewPostActivityView.postReviewSuccess(reviewResponse.getMessage());
                //등록 실패
                } else {
                    mReviewPostActivityView.postReviewFailure(reviewResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductPostReviewResponse> call, Throwable t) {
                mReviewPostActivityView.postReviewFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

}
