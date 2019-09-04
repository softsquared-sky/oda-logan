package my.project.project_oda.src.product.interfaces;

import java.util.List;
import my.project.project_oda.src.product.models.Result;
import my.project.project_oda.src.product.models.ReviewResult;

public interface ProductActivityView {

    void getProductDetailSuccess(Result result);
    void getProductDetailFailure(String message);

    void getReviewSuccess(List<ReviewResult> response);
    void getReviewFailure(String message);

}
