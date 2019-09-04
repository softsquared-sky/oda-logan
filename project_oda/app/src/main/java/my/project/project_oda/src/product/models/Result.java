package my.project.project_oda.src.product.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("qpp")
    private String qpp;

    @SerializedName("storeMethod")
    private String storeMethod;

    @SerializedName("origin")
    private String origin;

    @SerializedName("imageResult")
    private List<imageResult> imageResult;

    public String getQpp() {
        return qpp;
    }

    public String getStoreMethod() {
        return storeMethod;
    }

    public String getOrigin() {
        return origin;
    }

    public List<imageResult> getImageResult() {
        return imageResult;
    }
}