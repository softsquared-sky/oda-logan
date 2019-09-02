package my.project.project_oda.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.login.LoginActivity;
import my.project.project_oda.src.main.home.fragments.Fragment_home;
import my.project.project_oda.src.main.mypage.fragments.Fragment_mypage;
import my.project.project_oda.src.search.SearchActivity;

public class MainActivity extends BaseActivity {

    private FragmentManager mfragmentManager;
    private Fragment_home mFragmentHome;
    private Fragment_mypage mFragmentMyPage;
    private FragmentTransaction mTransaction;

    private ImageView mIvMainHome;
    private ImageView mIvMainMyPage;
    private TextView mTvMainHome;
    private TextView mTvMainMyPage;
    private FloatingActionButton mFloatingBtnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();
        this.setListener();
    }

    public void initialize(){

        mIvMainHome = findViewById(R.id.iv_main_home);
        mIvMainMyPage = findViewById(R.id.iv_main_mypage);
        mTvMainHome = findViewById(R.id.tv_main_home);
        mTvMainMyPage = findViewById(R.id.tv_main_mypage);
        mFloatingBtnMain = findViewById(R.id.fbtn_main);

    }

    public void setListener(){

        

    }

    public void onClick(View view){

        mTransaction = mfragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.iv_home_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_home:
                mTransaction.replace(R.id.frame_main, mFragmentHome).commitAllowingStateLoss();
                mIvMainHome.setImageResource(R.drawable.ic_home);
                mIvMainMyPage.setImageResource(R.drawable.ic_my_page_normal);
                mTvMainHome.setTextColor(getResources().getColor(R.color.splash_back));
                mTvMainMyPage.setTextColor(getResources().getColor(R.color.normal));
                mFloatingBtnMain.show();
                break;
            case R.id.linear_mypage:
                mTransaction.replace(R.id.frame_main, mFragmentMyPage).commitAllowingStateLoss();
                mIvMainHome.setImageResource(R.drawable.ic_home_normal);
                mIvMainMyPage.setImageResource(R.drawable.ic_my_page);
                mTvMainHome.setTextColor(getResources().getColor(R.color.normal));
                mTvMainMyPage.setTextColor(getResources().getColor(R.color.splash_back));
                mFloatingBtnMain.hide();
                break;
        }
    }

    public void setFragment(){

        mfragmentManager = getSupportFragmentManager();
        mFragmentHome = new Fragment_home();
        mFragmentMyPage = new Fragment_mypage();
        mTransaction = mfragmentManager.beginTransaction();
        mTransaction.replace(R.id.frame_main, mFragmentHome).commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplication(), LoginActivity.class));
    }
}
