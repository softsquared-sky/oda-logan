package my.project.project_oda.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.main.home.fragments.Fragment_home;
import my.project.project_oda.src.main.mypage.fragments.Fragment_mypage;
import my.project.project_oda.src.search.SearchActivity;

public class MainActivity extends BaseActivity {

    private FragmentManager mfragmentManager;
    private Fragment_home mFhome;
    private Fragment_mypage mFmypage;
    private FragmentTransaction mtransaction;

    private ImageView miv_main_home;
    private ImageView miv_main_mypage;
    private TextView mtv_main_home;
    private  TextView mtv_main_mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();
    }

    public void initialize(){

        miv_main_home = findViewById(R.id.iv_main_home);
        miv_main_mypage = findViewById(R.id.iv_main_mypage);
        mtv_main_home = findViewById(R.id.tv_main_home);
        mtv_main_mypage = findViewById(R.id.tv_main_mypage);

    }

    public void onClick(View view){

        mtransaction = mfragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.iv_home_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_home:
                mtransaction.replace(R.id.frame_main, mFhome).commitAllowingStateLoss();
                miv_main_home.setImageResource(R.drawable.icon_home);
                miv_main_mypage.setImageResource(R.drawable.shape);
                mtv_main_home.setTextColor(getResources().getColor(R.color.splash_back));
                mtv_main_mypage.setTextColor(getResources().getColor(R.color.normal));
                break;
            case R.id.linear_mypage:
                mtransaction.replace(R.id.frame_main, mFmypage).commitAllowingStateLoss();
                miv_main_home.setImageResource(R.drawable.icon_home_normal);
                miv_main_mypage.setImageResource(R.drawable.shape_pressed);
                mtv_main_home.setTextColor(getResources().getColor(R.color.normal));
                mtv_main_mypage.setTextColor(getResources().getColor(R.color.splash_back));
                break;
        }
    }

    public void setFragment(){

        mfragmentManager = getSupportFragmentManager();
        mFhome = new Fragment_home();
        mFmypage = new Fragment_mypage();
        mtransaction = mfragmentManager.beginTransaction();
        mtransaction.replace(R.id.frame_main, mFhome).commitAllowingStateLoss();

    }

}
