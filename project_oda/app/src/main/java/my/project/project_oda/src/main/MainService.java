package my.project.project_oda.src.main;

import my.project.project_oda.src.main.interfaces.MainActivityView;
import my.project.project_oda.src.main.interfaces.MainRetrofitInterface;
import my.project.project_oda.src.main.models.DefaultResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
