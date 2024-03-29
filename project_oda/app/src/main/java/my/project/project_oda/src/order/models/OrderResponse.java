package my.project.project_oda.src.order.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

}