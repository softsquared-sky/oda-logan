package my.project.project_oda.src.cart.models;

import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("pNum")
    private int pNum;
    @SerializedName("pName")
    private String pName;
    @SerializedName("imageUrl")
    private String image;
    @SerializedName("odaPrice")
    private int price;
    @SerializedName("stock")
    private int stock;
    @SerializedName("type")
    private String type;
    private boolean checked;
    private int count;


    public CartItem(int pNum, String pName, String image, int price, int stock, String type){
        this.pNum = pNum;
        this.pName = pName;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.checked = false;
        this.count = 0;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}