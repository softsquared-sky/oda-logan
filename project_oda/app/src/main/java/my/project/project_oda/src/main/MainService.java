package my.project.project_oda.src.main;

import android.util.Log;

import my.project.project_oda.src.main.interfaces.MainActivityView;
import my.project.project_oda.src.main.interfaces.MainRetrofitInterface;
import my.project.project_oda.src.main.models.CheckResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.*;

class MainService {
    private final MainActivityView mMainActivityView;
    private final String id;

    MainService(final MainActivityView mainActivityView,String id) {
        this.mMainActivityView = mainActivityView;
        this.id = id;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit_for_check().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest(id).enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                final CheckResponse checkResponse = response.body();
                if (checkResponse == null) {
                    mMainActivityView.DuplicateFailure(null);
                    return;
                }

                mMainActivityView.DuplicateSuccess(checkResponse.getMessage());
                Log.d(TAG, "중복확인 성공");
            }

            @Override
            public void onFailure(Call<CheckResponse> call, Throwable t) {
                mMainActivityView.DuplicateFailure(null);
                Log.d(TAG, "중복확인 실패");
            }
        });
    }
}
