package my.project.project_oda.src.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import my.project.project_oda.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }//onCreate finished

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_login:
                Intent LoginIntent = new Intent(this, HomeActivity.class);
                startActivity(LoginIntent);
                finish();
                break;
            case R.id.btn_sign_up:
                Intent SignUpintent = new Intent(this, SignUpActivity.class);
                startActivity(SignUpintent);
                break;
        }
    }//onClick finished

}//LoginActivity finished
