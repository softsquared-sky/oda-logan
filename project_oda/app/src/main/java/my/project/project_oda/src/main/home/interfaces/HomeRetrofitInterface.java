package my.project.project_oda.src.main.home.interfaces;

import my.project.project_oda.src.main.home.models.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {

    //상품들 가져오기
    @GET("/product")
    Call<ProductResponse> getProducts(@Query("pName") String pName, @Query("lastProductTurn") int lastProductTurn);


}
