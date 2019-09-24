package my.project.project_oda.src.search.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import my.project.project_oda.src.product.detail.models.Result;

public class PopularResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private List<PopularItem> result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public List<PopularItem> getResult() {
        return result;
    }
}