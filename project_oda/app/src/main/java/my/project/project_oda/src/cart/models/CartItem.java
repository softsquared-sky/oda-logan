package my.project.project_oda.src.cart.models;

public class CartItem {

    private int pNum;
    private String pName;
    private String image;
    private String price;
    private String stock;

    public CartItem(int pNum, String pName, String image, String price, String stock){
        this.pNum = pNum;
        this.pName = pName;
        this.image = image;
        this.price = price;
        this.stock = stock;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}