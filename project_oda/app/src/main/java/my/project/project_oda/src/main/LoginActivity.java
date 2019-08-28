package my.project.project_oda.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import my.project.project_oda.R;
import my.project.project_oda.src.ApplicationClass;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.main.interfaces.LoginActivityView;
import static my.project.project_oda.src.ApplicationClass.*;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private EditText medt_login_id;
    private EditText medt_login_password;
    private CheckBox mchbox_auto;

    SharedPreferences sf;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initialize();

    }//onCreate finished

    public void initialize(){

        medt_login_id = findViewById(R.id.edt_login_id);
        medt_login_password = findViewById(R.id.edt_login_password);
        mchbox_auto = findViewById(R.id.chbox_auto);

        //ApplicationClass mApp =((ApplicationClass)getApplicationContext());
        //sf = mApp.getSharedPreference();
        //editor = sf.edit();

        //editor = sSharedPreferences.edit();

    }//initialzie finished

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_login:
                //Intent LoginIntent = new Intent(this, HomeActivity.class);
                //startActivity(LoginIntent);
                //finish();
                login();
                break;
            case R.id.btn_sign_up:
                Intent SignUpintent = new Intent(this, SignUpActivity.class);
                startActivity(SignUpintent);
                break;
        }
    }//onClick finished

    private void login(){
        showProgressDialog();
        final MainService loginService = new MainService(this, medt_login_id.getText().toString(), medt_login_password.getText().toString());
        loginService.LogIn();
    }

    @Override
    public void LoginSuccess(String jwt,String text) {
        hideProgressDialog();
        showCustomToast(text);
        //로그인시 받아오는 jwt를 sharedpreference에 저장
        //editor.putString(X_ACCESS_TOKEN, jwt);
        //editor.commit();
        Log.d(TAG, jwt+"");
        Intent LoginIntent = new Intent(this, HomeActivity.class);
        startActivity(LoginIntent);
        Log.d(TAG, "로그인 성공 Home으로!");
        //finish();
    }

    @Override
    public void LoginFailure(String text) {
        hideProgressDialog();
        showCustomToast2(text);
    }
}//LoginActivity finished
