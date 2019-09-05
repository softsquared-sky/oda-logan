package my.project.project_oda.src.product;

import android.util.Log;
import org.json.JSONObject;
import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.interfaces.ProductRetrofitInterface;
import my.project.project_oda.src.product.models.BasketResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.*;

class ProductService {

    private ProductActivityView mProductActivityView;
    private JSONObject mProductNumberBody;
    private int mProductNumber;

    //기본조회 생성자
    ProductService(final ProductActivityView productActivityView, int productNumber) {
        this.mProductActivityView = productActivityView;
        this.mProductNumber = productNumber;
    }

    //장바구니 생성자
    ProductService(final ProductActivityView productActivityView, JSONObject mProductNumberBody) {
        this.mProductActivityView = productActivityView;
        this.mProductNumberBody = mProductNumberBody;
    }

    //상품기본정보 조회
    void getProductBasic() {

    }

    //장바구니 담기
    void postBasket() {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.postBasket(RequestBody.create(MEDIA_TYPE_JSON, mProductNumberBody.toString())).enqueue(new Callback<BasketResponse>() {
            @Override
            public void onResponse(Call<BasketResponse> call, Response<BasketResponse> response) {

                final BasketResponse basketResponse = response.body();
                if (basketResponse == null) {
                    mProductActivityView.postBasketFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //담기 성공
                if (basketResponse.getIsSuccess()) {
                    mProductActivityView.postBasketSuccess(basketResponse.getMessage());
                //담기 실패
                } else {
                    mProductActivityView.postBasketFailure(basketResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BasketResponse> call, Throwable t) {
                mProductActivityView.postBasketFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });

    }

}
