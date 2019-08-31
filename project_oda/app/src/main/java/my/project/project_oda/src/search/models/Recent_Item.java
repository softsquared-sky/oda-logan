package my.project.project_oda.src.search.models;

public class Recent_Item {

    private String keyword;

    public Recent_Item(){

    }

    public Recent_Item(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}