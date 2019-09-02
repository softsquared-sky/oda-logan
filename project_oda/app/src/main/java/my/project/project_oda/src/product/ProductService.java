package my.project.project_oda.src.product;

import android.util.Log;

import org.json.JSONObject;
import my.project.project_oda.src.product.models.ProductDetailResponse;
import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.interfaces.ProductRetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class ProductService {

    private ProductActivityView mProductActivityView;
    private int pNum;

    ProductService(final ProductActivityView productActivityView, int pNum ) {
        this.mProductActivityView = productActivityView;
        this.pNum = pNum;
    }

    //상품상세
    void getProductDetail() {

        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductDetail(pNum).enqueue(new Callback<ProductDetailResponse>() {
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
                //로그인 성공
                if (detailResponse.getIsSuccess()) {
                    mProductActivityView.getProductDetailSuccess(detailResponse.getResult());
                //로그인 실패
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
}
