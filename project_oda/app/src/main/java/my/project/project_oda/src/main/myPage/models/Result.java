package my.project.project_oda.src.main.myPage.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("payDate")
    private String payDate;
    @SerializedName("payDegree")
    private int payDegree;
    @SerializedName("deliveryStatus")
    private String deliveryStatus;
    @SerializedName("orderList")
    private List<PayList> orderList;

    public String getPayDate() {
        return payDate;
    }

    public int getPayDegree() {
        return payDegree;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public List<PayList> getOrderList() {
        return orderList;
    }
}