package my.project.project_oda.src.product.review.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import my.project.project_oda.src.product.review.models.ReviewResult;

public class ProductReviewResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private List<ReviewResult> result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public List<ReviewResult> getResult() {
        return result;
    }
}