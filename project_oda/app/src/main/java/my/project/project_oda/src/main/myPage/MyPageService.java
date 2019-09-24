package my.project.project_oda.src.main.myPage;

import android.util.Log;

import my.project.project_oda.src.main.myPage.interfaces.MyPageActivityView;
import my.project.project_oda.src.main.myPage.interfaces.MyPageRetrofitInterface;
import my.project.project_oda.src.main.myPage.models.MyPageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

public class MyPageService {

    private MyPageActivityView mMyPageActivityView;

    public MyPageService(final MyPageActivityView mMyPageActivityView) {
        this.mMyPageActivityView = mMyPageActivityView;
    }

    //상품들 가져오기
    public void getMyPage() {

        final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.getMyPage().enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {
                if (response == null) {
                    mMyPageActivityView.getMyPageFailure("fail");
                    return;
                }

                final MyPageResponse myPageResponse = response.body();
                if (myPageResponse == null) {
                    mMyPageActivityView.getMyPageFailure("응답 없음");
                    Log.d(TAG, "응답 없음");
                    return;
                }
                //바로주문에 넣기 성공
                //1100 : 이전에 결제한 내역이 있음
                if (myPageResponse.getCode() == 1100) {
                    mMyPageActivityView.getMyPageSuccess(myPageResponse.getResult());
                //바로주문에 넣기 실패
                } else {
                    mMyPageActivityView.getMyPageFailure(myPageResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                mMyPageActivityView.getMyPageFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });
    }

}
