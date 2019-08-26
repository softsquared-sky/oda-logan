package my.project.project_oda.src.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import my.project.project_oda.R;

public class SignInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }//onCreate finished

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_duplicate:
                break;
            case R.id.iv_back_arrow:
                finish();
                break;
        }
    }//onClick finished

}//LoginActivity finished
