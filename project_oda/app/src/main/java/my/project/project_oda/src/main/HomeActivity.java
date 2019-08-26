package my.project.project_oda.src.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import my.project.project_oda.R;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View view){
        switch (view.getId()){

        }
    }
}
