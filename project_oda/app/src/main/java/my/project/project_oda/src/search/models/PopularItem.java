package my.project.project_oda.src.search.models;

import com.google.gson.annotations.SerializedName;

public class PopularItem {

    @SerializedName("word")
    private String keyword;

    private int order;

    public PopularItem(String keyword, int order){
        this.keyword = keyword;
        this.order = order;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}