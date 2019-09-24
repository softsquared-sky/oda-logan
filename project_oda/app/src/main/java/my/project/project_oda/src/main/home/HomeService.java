package my.project.project_oda.src.main.home;

import android.util.Log;

import org.json.JSONObject;

import my.project.project_oda.src.main.home.interfaces.HomeActivityView;
import my.project.project_oda.src.main.home.interfaces.HomeRetrofitInterface;
import my.project.project_oda.src.main.home.models.DirectOrderResponse;
import my.project.project_oda.src.main.home.models.ProductResponse;
import my.project.project_oda.src.main.interfaces.MainRetrofitInterface;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

public class HomeService {

    private HomeActivityView mHomeActivityView;
    private String mProductName;
    private int mLastProductTurn;
    private JSONObject mParams;

    public HomeService(final HomeActivityView homeActivityView, String mProductName, int mLastProductTurn) {
        this.mHomeActivityView = homeActivityView;
        this.mProductName = mProductName;
        this.mLastProductTurn = mLastProductTurn;
    }

    public HomeService(final HomeActivityView homeActivityView, JSONObject mParams) {
        this.mHomeActivityView = homeActivityView;
        this.mParams = mParams;
    }

    //상품들 가져오기
    public void getProducts() {

        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.getProducts(mProductName, mLastProductTurn).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response == null) {
                    mHomeActivityView.getProductFailure("fail");
                    return;
                }

                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mHomeActivityView.getProductFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //바로주문에 넣기 성공
                if (productResponse.isSuccess()) {
                    mHomeActivityView.getProductSuccess(productResponse.getResult());
                    //바로주문에 넣기 실패
                } else {
                    mHomeActivityView.getProductFailure(productResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mHomeActivityView.getProductFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

    //바로 주문
    public void postDirectOrder() {

        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.postDirectOrder(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<DirectOrderResponse>() {
            @Override
            public void onResponse(Call<DirectOrderResponse> call, Response<DirectOrderResponse> response) {
                if (response == null) {
                    mHomeActivityView.postDirectOrderFailure("fail");
                    return;
                }

                final DirectOrderResponse orderResponse = response.body();
                if (orderResponse == null) {
                    mHomeActivityView.postDirectOrderFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //바로주문에 넣기 성공
                if (orderResponse.isSuccess()) {
                    mHomeActivityView.postDirectOrderSuccess(orderResponse.getMessage());
                    Log.d(TAG, "success");
                    //바로주문에 넣기 실패
                } else {
                    mHomeActivityView.postDirectOrderFailure(orderResponse.getMessage());
                    Log.d(TAG, "success");
                }
            }

            @Override
            public void onFailure(Call<DirectOrderResponse> call, Throwable t) {
                mHomeActivityView.postDirectOrderFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }
}
