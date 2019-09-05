package my.project.project_oda.src.product.detail;

import android.util.Log;

import my.project.project_oda.src.product.detail.interfaces.DetailFragmentView;
import my.project.project_oda.src.product.detail.interfaces.DetailRetrofitInterface;
import my.project.project_oda.src.product.detail.models.ProductDetailResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class DetailService {

    private DetailFragmentView mDetailFragmentView;
    private int mProductNumber;

    DetailService(final DetailFragmentView mDetailFragmentView, int mProductNumber ) {
        this.mDetailFragmentView = mDetailFragmentView;
        this.mProductNumber = mProductNumber;
    }

    //상품상세
    void getProductDetail() {

        final DetailRetrofitInterface detailRetrofitInterface = getRetrofit().create(DetailRetrofitInterface.class);
        detailRetrofitInterface.getProductDetail(mProductNumber).enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response == null) {
                    mDetailFragmentView.getProductDetailFailure("fail");
                    return;
                }

                final ProductDetailResponse detailResponse = response.body();
                if (detailResponse == null) {
                    mDetailFragmentView.getProductDetailFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //정보 받기 성공
                if (detailResponse.getIsSuccess()) {
                    mDetailFragmentView.getProductDetailSuccess(detailResponse.getResult());
                //정보 받기 실패
                } else {
                    mDetailFragmentView.getProductDetailFailure(detailResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                mDetailFragmentView.getProductDetailFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

}
