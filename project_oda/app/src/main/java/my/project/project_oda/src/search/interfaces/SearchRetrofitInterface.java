package my.project.project_oda.src.search.interfaces;

import my.project.project_oda.src.product.detail.models.ProductDetailResponse;
import my.project.project_oda.src.product.models.BasketResponse;
import my.project.project_oda.src.search.models.PopularResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchRetrofitInterface {

    //상품상세
    @GET("/popularWord")
    Call<PopularResponse> getPopular();

}
