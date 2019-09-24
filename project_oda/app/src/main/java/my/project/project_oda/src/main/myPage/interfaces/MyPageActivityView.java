package my.project.project_oda.src.main.myPage.interfaces;

import java.util.List;

import my.project.project_oda.src.main.myPage.models.PayList;
import my.project.project_oda.src.main.myPage.models.Result;

public interface MyPageActivityView {

    void getMyPageSuccess(List<Result> result);
    void getMyPageFailure(String message);

}
