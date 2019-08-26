package my.project.project_oda.src.main.models;

public class SignUpForm {

    private String id;
    private String password;
    private String business_number;
    private String post;
    private int catering;

    public SignUpForm(){

    }

    public SignUpForm(String id, String password, String business_number, String post, int catering){
        this.id = id;
        this.password = password;
        this.business_number = business_number;
        this.post = post;
        this.catering = catering;
    }

    public int getCatering() {
        return catering;
    }

    public void setCatering(int catering) {
        this.catering = catering;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getBusiness_number() {
        return business_number;
    }

    public void setBusiness_number(String business_number) {
        this.business_number = business_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}