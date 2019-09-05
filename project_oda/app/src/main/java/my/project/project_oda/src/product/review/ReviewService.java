package my.project.project_oda.src.product.review;

import android.util.Log;

import my.project.project_oda.src.product.review.interfaces.ReviewFragmentView;
import my.project.project_oda.src.product.review.interfaces.ReviewRetrofitInterface;
import my.project.project_oda.src.product.review.models.ProductReviewResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class ReviewService {

    private ReviewFragmentView mReviewFragmentView;
    private int mProductNumber;

    ReviewService(final ReviewFragmentView mReviewFragmentView, int mProductNumber ) {
        this.mReviewFragmentView = mReviewFragmentView;
        this.mProductNumber = mProductNumber;
    }

    //상품 후기 조회
    void getProductReview(){

        final ReviewRetrofitInterface reviewRetrofitInterface = getRetrofit().create(ReviewRetrofitInterface.class);
        reviewRetrofitInterface.getProductReview(mProductNumber).enqueue(new Callback<ProductReviewResponse>() {
            @Override
            public void onResponse(Call<ProductReviewResponse> call, Response<ProductReviewResponse> response) {

                final ProductReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mReviewFragmentView.getReviewFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //조회 성공
                if (reviewResponse.getIsSuccess()) {
                    mReviewFragmentView.getReviewSuccess(reviewResponse.getResult());
                //조회 실패
                } else {
                    mReviewFragmentView.getReviewFailure(reviewResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductReviewResponse> call, Throwable t) {
                mReviewFragmentView.getReviewFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

}
