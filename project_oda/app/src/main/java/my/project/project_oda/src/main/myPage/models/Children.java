package my.project.project_oda.src.main.myPage.models;

public class Children {

    private String mImage;
    private String mTitle;
    private String mPrice;
    private String mCount;
    private String mDelivery;

    public Children(String mImage, String mTitle, String mPrice, String mCount, String mDelivery){
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mCount = mCount;
        this.mDelivery = mDelivery;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmCount() {
        return mCount;
    }

    public void setmCount(String mCount) {
        this.mCount = mCount;
    }

    public String getmDelivery() {
        return mDelivery;
    }

    public void setmDelivery(String mDelivery) {
        this.mDelivery = mDelivery;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}