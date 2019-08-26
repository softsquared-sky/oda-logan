package my.project.project_oda.src.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import my.project.project_oda.R;
import my.project.project_oda.src.Splash.Splash;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }//onCreate finished

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_login:
                break;
            case R.id.btn_sign_in:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;

        }
    }//onClick finished

}//LoginActivity finished
