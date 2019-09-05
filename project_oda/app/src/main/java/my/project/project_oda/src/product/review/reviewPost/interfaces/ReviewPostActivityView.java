package my.project.project_oda.src.product.review.reviewPost.interfaces;

import java.util.List;

import my.project.project_oda.src.product.review.models.ReviewResult;

public interface ReviewPostActivityView {

    void postReviewSuccess(String message);
    void postReviewFailure(String message);

}
