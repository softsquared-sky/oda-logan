package my.project.project_oda.src.cart;

import android.util.Log;

import my.project.project_oda.src.cart.interfaces.CartActivityView;
import my.project.project_oda.src.cart.interfaces.CartRetrofitInterface;
import my.project.project_oda.src.cart.models.CartResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class CartService {
    private CartActivityView mCartActivityView;

    //장바구니 조회 생성자
    CartService(final CartActivityView mCartActivityView) {
        this.mCartActivityView = mCartActivityView;
    }

    //장바구니 조회
    void getBasket() {

        final CartRetrofitInterface cartRetrofitInterface = getRetrofit().create(CartRetrofitInterface.class);
        cartRetrofitInterface.getBasket().enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response == null) {
                    mCartActivityView.getBasketFailure("fail");
                    return;
                }

                final CartResponse cartResponse = response.body();
                if (cartResponse == null) {
                    mCartActivityView.getBasketFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //조회 성공
                if (cartResponse.isSuccess()) {
                    mCartActivityView.getBasketSuccess(cartResponse.getBasketList(), cartResponse.getCode());
                    //조회 실패
                } else {
                    mCartActivityView.getBasketFailure(cartResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                mCartActivityView.getBasketFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }
}
