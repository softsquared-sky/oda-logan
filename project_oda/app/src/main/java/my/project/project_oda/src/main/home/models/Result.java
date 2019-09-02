package my.project.project_oda.src.main.home.models;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("pNum")
    private int pNum;

    @SerializedName("pName")
    private String pName;

    @SerializedName("odaPrice")
    private int odaPrice;

    @SerializedName("imageUrl")
    private String imageUrl;

    public int getpNum() {
        return pNum;
    }

    public String getpName() {
        return pName;
    }

    public int getOdaPrice() {
        return odaPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}