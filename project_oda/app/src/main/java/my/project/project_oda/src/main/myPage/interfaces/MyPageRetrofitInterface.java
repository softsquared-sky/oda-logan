package my.project.project_oda.src.main.myPage.interfaces;

import my.project.project_oda.src.main.myPage.models.MyPageResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyPageRetrofitInterface {

    //마이페이지 리스트 가져오기
    @GET("/mypage")
    Call<MyPageResponse> getMyPage();


}
