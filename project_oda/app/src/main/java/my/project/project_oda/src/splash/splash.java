package my.project.project_oda.src.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import my.project.project_oda.R;
import my.project.project_oda.src.login.LoginActivity;
import my.project.project_oda.src.main.MainActivity;

import static my.project.project_oda.src.ApplicationClass.*;

public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 2000); // 1초 후에 hd handler 실행  3000ms = 3초

    }

    private class splashhandler implements Runnable {
        public void run() {
            if(sSharedPreferences.getBoolean("auto",false)){
                startActivity(new Intent(getApplication(), MainActivity.class));
                splash.this.finish();
            }else {
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
