package my.project.project_oda.src.main.home.interfaces;

import my.project.project_oda.src.signup.models.CheckResponse;
import my.project.project_oda.src.signup.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {

    //아이디 중복확인용
    @GET("/product")
    Call<CheckResponse> getDuplicate(@Query("pName") String pName, @Query("lastProductTurn") String lastProductTurn);


}
