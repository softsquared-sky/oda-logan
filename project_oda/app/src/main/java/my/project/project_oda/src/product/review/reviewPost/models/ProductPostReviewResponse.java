package my.project.project_oda.src.product.review.reviewPost.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ProductPostReviewResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("date")
    private Date date;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public Date getDate() {
        return date;
    }
}