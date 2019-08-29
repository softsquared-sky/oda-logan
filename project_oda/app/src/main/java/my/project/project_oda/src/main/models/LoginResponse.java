package my.project.project_oda.src.main.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import my.project.project_oda.config.XAccessTokenInterceptor;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private JsonObject result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public JsonObject getResult() {
        return result;
    }
}