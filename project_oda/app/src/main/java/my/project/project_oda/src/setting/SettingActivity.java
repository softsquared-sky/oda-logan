package my.project.project_oda.src.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.login.LoginActivity;

import static my.project.project_oda.src.ApplicationClass.*;


public class SettingActivity extends BaseActivity {

    private Switch mSwitchPush;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.initialize();
    }

    public void initialize() {

        mEditor = sSharedPreferences.edit();
        mSwitchPush = findViewById(R.id.switch_setting_push);
        mSwitchPush.setChecked(sSharedPreferences.getBoolean("push", false));
        mSwitchPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mEditor.putBoolean("push", b);
                mEditor.apply();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.linear_logout:
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putString(X_ACCESS_TOKEN, "");
                editor.putBoolean("auto", false);
                editor.apply();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}
