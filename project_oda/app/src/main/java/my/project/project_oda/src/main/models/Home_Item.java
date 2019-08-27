package my.project.project_oda.src.main.models;

public class Home_Item {

    private String imageUrl;
    private String title;
    private int price;

    public Home_Item(){

    }

    public Home_Item(String imageUrl, String title, int price){
        this.imageUrl = imageUrl;
        this. title = title;
        this. price = price;
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
}