package my.project.project_oda.src.order.models;

public class OrderItem {

    private int pNum;
    private String orderImage;
    private String orderTitle;
    private int orderPrice;
    private int orderNumOfProduct;

    public OrderItem(int pNum, String orderTitle, int orderPrice, String orderImage, int orderNumOfProduct){
        this.pNum = pNum;
        this.orderTitle = orderTitle;
        this.orderPrice = orderPrice;
        this.orderImage = orderImage;
        this.orderNumOfProduct = orderNumOfProduct;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderNumOfProduct() {
        return orderNumOfProduct;
    }

    public void setOrderNumOfProduct(int orderNumOfProduct) {
        this.orderNumOfProduct = orderNumOfProduct;
    }
}