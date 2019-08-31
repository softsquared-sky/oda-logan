package my.project.project_oda.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.login.interfaces.LoginActivityView;
import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.signup.SignUpActivity;

import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private EditText medt_login_id;
    private EditText medt_login_password;
    private CheckBox mchbox_auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initialize();

        if(sSharedPreferences.getBoolean("auto",false)){
            String id = sSharedPreferences.getString("id", "");
            String pw = sSharedPreferences.getString("pw", "");
            login(id, pw);
            showCustomToast2(id+"로 자동 로그인");
        }

    }//onCreate finished

    public void initialize(){

        medt_login_id = findViewById(R.id.edt_login_id);
        medt_login_password = findViewById(R.id.edt_login_password);
        mchbox_auto = findViewById(R.id.chbox_auto);

    }//initialzie finished

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_login:
                //Intent LoginIntent = new Intent(this, MainActivity.class);
                //startActivity(LoginIntent);
                //finish();
                login(medt_login_id.getText().toString(), medt_login_password.getText().toString());
                break;
            case R.id.btn_sign_up:
                Intent SignUpintent = new Intent(this, SignUpActivity.class);
                startActivity(SignUpintent);
                break;
        }
    }//onClick finished

    private void login(String id, String pw) {
        showProgressDialog();
        final LoginService loginService = new LoginService(this, id, pw);
        loginService.LogIn();
    }

    @Override
    public void LoginSuccess(String text) {
        hideProgressDialog();
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
        showCustomToast(text);
        Log.d(TAG, "받은 text: " + text);

        //자동 로그인 체크
        if(mchbox_auto.isChecked()){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString("id", medt_login_id.getText().toString());
            editor.putString("pw",medt_login_password.getText().toString());
            editor.putBoolean("auto", true);
            editor.apply();
        }else{
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString("id", null);
            editor.putString("pw", null);
            editor.putBoolean("auto",false);
            editor.apply();
        }
        //다시 로그인 액티비티로 돌아갈 수 있게 할지 정하기
        //finish();
    }

    @Override
    public void LoginFailure(String text) {
        hideProgressDialog();
        showCustomToast2(text);
        Log.d(TAG, "로그인 실패!");
    }

    @Override
    public void onStop() {
        super.onStop();
        medt_login_id.setText(null);
        medt_login_password.setText(null);
        medt_login_password.clearFocus();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putBoolean("auto", false);
        editor.apply();
    }
}//LoginActivity finished
