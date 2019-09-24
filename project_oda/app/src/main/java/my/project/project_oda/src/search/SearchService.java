package my.project.project_oda.src.search;

import android.util.Log;

import org.json.JSONObject;

import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.interfaces.ProductRetrofitInterface;
import my.project.project_oda.src.product.models.BasketResponse;
import my.project.project_oda.src.search.interfaces.SearchActivityView;
import my.project.project_oda.src.search.interfaces.SearchRetrofitInterface;
import my.project.project_oda.src.search.models.PopularResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.getRetrofit;

public class SearchService {

    private SearchActivityView mSearchActivityView;
    private int mProductNumber;

    //인기검색어 생성자
    public SearchService(final SearchActivityView mSearchActivityView) {
        this.mSearchActivityView = mSearchActivityView;
    }

    //인기검색어
    public void getPopular() {
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.getPopular().enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {

                final PopularResponse popularResponse = response.body();
                if (popularResponse == null) {
                    mSearchActivityView.getPopularFailure("응답 없음");
                    //Log.d(TAG, "응답 없음");
                    return;
                }
                //담기 성공
                if (popularResponse.getIsSuccess()) {
                    mSearchActivityView.getPopularSuccess(popularResponse.getResult());
                //담기 실패
                } else {
                    mSearchActivityView.getPopularFailure(popularResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                mSearchActivityView.getPopularFailure("서버 연결 실패");
                Log.d(TAG, "Failure");
            }
        });

    }

}
