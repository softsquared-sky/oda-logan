package my.project.project_oda.src.main.home.models;

public class Home_Item {

    private String imageUrl;
    private String title;
    private int price;
    private int pNum;
    private boolean isChecked;

    public Home_Item(String imageUrl, String title, int price, boolean isChecked, int pNum){
        this.imageUrl = imageUrl;
        this. title = title;
        this. price = price;
        this.isChecked = isChecked;
        this.pNum = pNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }
}