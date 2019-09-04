package my.project.project_oda.src.product;

import android.util.Log;

import org.json.JSONObject;
import my.project.project_oda.src.product.models.ProductDetailResponse;
import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.interfaces.ProductRetrofitInterface;
import my.project.project_oda.src.product.models.ProductReviewResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class ProductService {

    private ProductActivityView mProductActivityView;
    private int mProductNumber;

    ProductService(final ProductActivityView productActivityView, int mProductNumber ) {
        this.mProductActivityView = productActivityView;
        this.mProductNumber = mProductNumber;
    }

    //상품상세
    void getProductDetail() {

        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductDetail(mProductNumber).enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response == null) {
                    mProductActivityView.getProductDetailFailure("fail");
                    return;
                }

                final ProductDetailResponse detailResponse = response.body();
                if (detailResponse == null) {
                    mProductActivityView.getProductDetailFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //정보 받기 성공
                if (detailResponse.getIsSuccess()) {
                    mProductActivityView.getProductDetailSuccess(detailResponse.getResult());
                //정보 받기 실패
                } else {
                    mProductActivityView.getProductDetailFailure(detailResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                mProductActivityView.getProductDetailFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

    //상품 후기 조회
    void getProductReview(){

        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductReview(mProductNumber).enqueue(new Callback<ProductReviewResponse>() {
            @Override
            public void onResponse(Call<ProductReviewResponse> call, Response<ProductReviewResponse> response) {
                if (response == null) {
                    mProductActivityView.getReviewFailure("fail");
                    return;
                }

                final ProductReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mProductActivityView.getReviewFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //조회 성공
                if (reviewResponse.getIsSuccess()) {
                    mProductActivityView.getReviewSuccess(reviewResponse.getResult());
                //조회 실패
                } else {
                    mProductActivityView.getReviewFailure(reviewResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductReviewResponse> call, Throwable t) {
                mProductActivityView.getReviewFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });

    }
}
