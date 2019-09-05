package my.project.project_oda.src.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONObject;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.login.LoginActivity;
import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.splash.interfaces.SplashActivityView;

import static my.project.project_oda.src.ApplicationClass.*;

public class splash extends Activity implements SplashActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1000); // 1초 후에 hd handler 실행

    }

    private void postAutoLogin() {
        JSONObject params = new JSONObject();
        final splashService splashService = new splashService(this, params, this);
        splashService.postAutoLogin();
    }

    @Override
    public void postAutoLoginSuccess(int code) {
        //201 이면 유효하지 않은 토큰
        if(code == 201) {
            startActivity(new Intent(getApplication(), LoginActivity.class)); //토큰 유효하지 않을 시 로그인으로 이동
            splash.this.finish(); // 로딩페이지 Activity stack에서 제거
            Toast.makeText(this, getString(R.string.network_auto_login_fail),Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(getApplication(), MainActivity.class));    //토큰 유효시 메인으로 이동
            splash.this.finish();
            Toast.makeText(this, getString(R.string.network_auto_login),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void postAutoLoginFailure(String message) {

    }

    private class splashhandler implements Runnable {
        public void run() {
            if (sSharedPreferences.getBoolean("auto", false)) {
                postAutoLogin();
            } else {
                startActivity(new Intent(getApplication(), LoginActivity.class)); //로딩이 끝난 후, ChoiceFunction 이동
                splash.this.finish(); // 로딩페이지 Activity stack에서 제거
            }
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }
}
