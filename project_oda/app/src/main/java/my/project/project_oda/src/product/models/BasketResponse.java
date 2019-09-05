package my.project.project_oda.src.product.models;

import com.google.gson.annotations.SerializedName;

import my.project.project_oda.src.product.detail.models.Result;

public class BasketResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("pName")
    private String pName;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public String getpName() {
        return pName;
    }
}