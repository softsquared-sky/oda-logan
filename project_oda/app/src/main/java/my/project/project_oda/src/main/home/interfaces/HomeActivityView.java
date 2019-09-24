package my.project.project_oda.src.main.home.interfaces;

import java.util.List;

import my.project.project_oda.src.main.home.models.Result;

public interface HomeActivityView {

    void getProductSuccess(List<Result> resultList);
    void getProductFailure(String message);

    void postDirectOrderSuccess(String message);
    void postDirectOrderFailure(String message);

}
