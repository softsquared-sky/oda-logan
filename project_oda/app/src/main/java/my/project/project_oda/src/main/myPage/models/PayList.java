package my.project.project_oda.src.main.myPage.models;

import com.google.gson.annotations.SerializedName;

public class PayList {

    @SerializedName("paySeq")
    private int paySeq;
    @SerializedName("amount")
    private int amount;
    @SerializedName("odaPrice")
    private int odaPrice;
    @SerializedName("pName")
    private String pName;
    @SerializedName("pNum")
    private int pNum;
    @SerializedName("imageUrl")
    private String imageUrl;

    public int getPaySeq() {
        return paySeq;
    }

    public int getAmount() {
        return amount;
    }

    public int getOdaPrice() {
        return odaPrice;
    }

    public String getpName() {
        return pName;
    }

    public int getpNum() {
        return pNum;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}