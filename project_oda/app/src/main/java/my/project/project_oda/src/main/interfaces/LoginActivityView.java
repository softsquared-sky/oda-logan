package my.project.project_oda.src.main.interfaces;

public interface LoginActivityView {

    void LoginSuccess(String jwt, String text);

    void LoginFailure(String text);

}
