package my.project.project_oda.src.order;

import android.util.Log;
import org.json.JSONObject;
import my.project.project_oda.src.order.interfaces.OrderActivityView;
import my.project.project_oda.src.order.interfaces.OrderRetrofitInterface;
import my.project.project_oda.src.order.models.OrderResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class OrderService {
    private OrderActivityView mOrderActivityView;
    JSONObject mParams;

    //주문요청
    OrderService(final OrderActivityView mOrderActivityView, JSONObject mParams) {
        this.mOrderActivityView = mOrderActivityView;
        this.mParams = mParams;
    }

    //로그인
    void postOrderRequest() {

        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);
        orderRetrofitInterface.postOrderRequest(RequestBody.create(MEDIA_TYPE_JSON, mParams.toString())).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response == null) {
                    mOrderActivityView.postOrderRequestFailure("fail");
                    return;
                }

                final OrderResponse orderResponse = response.body();
                if (orderResponse == null) {
                    mOrderActivityView.postOrderRequestFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //주문 성공
                if (orderResponse.getIsSuccess()) {
                    mOrderActivityView.postOrderRequestSuccess(orderResponse.getMessage());
                    //주문 실패
                } else {
                    mOrderActivityView.postOrderRequestFailure(orderResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                mOrderActivityView.postOrderRequestFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }
}
