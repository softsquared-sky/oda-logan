package my.project.project_oda.src.main.home.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductNumber implements Serializable {

    @SerializedName("pNum")
    private int pNum;

    public ProductNumber(int mProductNum) {
        this.pNum = mProductNum;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }
}