package my.project.project_oda.src.product.detail.interfaces;

import java.util.List;

import my.project.project_oda.src.product.detail.models.Result;
import my.project.project_oda.src.product.review.models.ReviewResult;

public interface DetailFragmentView {

    void getProductDetailSuccess(Result result);
    void getProductDetailFailure(String message);

}
