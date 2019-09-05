package my.project.project_oda.src.product.detail.models;

import com.google.gson.annotations.SerializedName;

public class imageResult {
    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("turn")
    private int turn;

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTurn() {
        return turn;
    }
}