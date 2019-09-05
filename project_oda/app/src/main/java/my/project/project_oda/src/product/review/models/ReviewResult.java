package my.project.project_oda.src.product.review.models;

import com.google.gson.annotations.SerializedName;

public class ReviewResult {
    @SerializedName("id")
    private String id;

    @SerializedName("review")
    private String review;

    @SerializedName("reviewDate")
    private String reviewDate;

    @SerializedName("reviewImage")
    private String reviewImage;

    public String getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public String getReviewImage() {
        return reviewImage;
    }

    public String getReviewDate() {
        return reviewDate;
    }
}