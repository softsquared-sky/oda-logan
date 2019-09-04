package my.project.project_oda.src.product.models;

import com.google.gson.annotations.SerializedName;

public class ReviewResult {
    @SerializedName("id")
    private int id;

    @SerializedName("review")
    private String review;

    @SerializedName("reviewDate")
    private boolean reviewDate;

    @SerializedName("reviewImage")
    private String reviewImage;

    public int getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public boolean isReviewDate() {
        return reviewDate;
    }

    public String getReviewImage() {
        return reviewImage;
    }
}