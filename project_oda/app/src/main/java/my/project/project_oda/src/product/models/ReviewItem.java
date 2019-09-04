package my.project.project_oda.src.product.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ReviewItem {

    private int pNum;
    private String reviewTitle;
    private String review;
    private String reviewId;
    private String reviewImage;
    private String reviewDate;

    public ReviewItem(int pNum, String reviewTitle, String review, String reviewImage, String reviewId, String reviewDate){
        this.pNum = pNum;
        this.reviewTitle = reviewTitle;
        this.review = review;
        this.reviewImage = reviewImage;
        this.reviewId = reviewId;
        this.reviewDate = reviewDate;
    }

    public String getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(String reviewImage) {
        this.reviewImage = reviewImage;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}