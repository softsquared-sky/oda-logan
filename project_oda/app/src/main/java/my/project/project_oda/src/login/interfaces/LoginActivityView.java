package my.project.project_oda.src.login.interfaces;

import com.google.gson.JsonObject;

public interface LoginActivityView {

    void LoginSuccess(String text, String  jwt);

    void LoginFailure(String text);

}
