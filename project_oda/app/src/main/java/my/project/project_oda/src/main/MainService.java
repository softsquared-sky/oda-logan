package my.project.project_oda.src.main;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import my.project.project_oda.src.main.interfaces.MainActivityView;
import my.project.project_oda.src.main.interfaces.MainRetrofitInterface;
import my.project.project_oda.src.main.models.DirectOrderResponse;
import my.project.project_oda.src.main.mypage.models.ProductNumber;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.*;

class MainService {

    MainActivityView mMainActivityView;
    private List<ProductNumber> mProductNumberList;
    MainService(final MainActivityView mainActivityView, List<ProductNumber>mProductNumberList) {
        this.mMainActivityView = mainActivityView;
        this.mProductNumberList = mProductNumberList;
    }

    void postDirectOrder() {

        JSONObject params;
        try {
            params = new JSONObject();
            //params.put("id", mId);
            //params.put("pw", mPw);
        } catch (Exception e) {
            //Log.d(TAG, "error: " + e);
            return;
        }

        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postDirectOrder(RequestBody.create(MEDIA_TYPE_JSON, params.toString())).enqueue(new Callback<DirectOrderResponse>() {
            @Override
            public void onResponse(Call<DirectOrderResponse> call, Response<DirectOrderResponse> response) {
                if (response == null) {
                    mMainActivityView.postDirectOrderFailure("fail");
                    return;
                }

                final DirectOrderResponse orderResponse = response.body();
                if (orderResponse == null) {
                    mMainActivityView.postDirectOrderFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //바로주문에 넣기 성공
                if (orderResponse.isSuccess()) {
                    mMainActivityView.postDirectOrderSuccess(orderResponse.getMessage());

                //바로주문에 넣기 실패
                } else {
                    mMainActivityView.postDirectOrderFailure(orderResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<DirectOrderResponse> call, Throwable t) {
                mMainActivityView.postDirectOrderFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

}
