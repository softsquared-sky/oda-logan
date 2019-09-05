package my.project.project_oda.src.product.review.interfaces;

import java.util.List;
import my.project.project_oda.src.product.review.models.ReviewResult;

public interface ReviewFragmentView {

    void getReviewSuccess(List<ReviewResult> response);
    void getReviewFailure(String message);

}
