package my.project.project_oda.src.main.home.models;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("result")
    private Result result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}