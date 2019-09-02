package my.project.project_oda.src.product.interfaces;

import com.google.gson.JsonObject;

import my.project.project_oda.src.product.models.ProductDetailResponse;

public interface ProductActivityView {

    public void getProductDetailSuccess(JsonObject response);
    public void getProductDetailFailure(String message);

}
