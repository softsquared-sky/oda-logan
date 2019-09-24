package my.project.project_oda.src.main.home.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductNumberList {

    @SerializedName("pNumList")
    private ArrayList<ProductNumber> pNumList;

    public ProductNumberList(ArrayList<ProductNumber> mProductNumList) {
        this.pNumList = mProductNumList;
    }
}