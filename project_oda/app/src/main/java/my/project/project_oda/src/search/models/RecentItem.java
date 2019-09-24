package my.project.project_oda.src.search.models;

public class RecentItem {

    private String keyword;

    public RecentItem(){

    }

    public RecentItem(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}