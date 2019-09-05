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
import my.project.project_oda.src.cart.CartActivity;
import my.project.project_oda.src.login.LoginActivity;
import my.project.project_oda.src.main.home.fragments.FragmentHome;
import my.project.project_oda.src.main.interfaces.MainActivityView;
import my.project.project_oda.src.main.mypage.fragments.Fragment_mypage;
import my.project.project_oda.src.search.SearchActivity;
import my.project.project_oda.src.setting.SettingActivity;

public class MainActivity extends BaseActivity implements MainActivityView {

    private FragmentManager mFragmentManager;
    private FragmentHome mFragmentHome;
    private Fragment_mypage mFragmentMyPage;
    private FragmentTransaction mTransaction;

    private ImageView mIvMainHome;
    private ImageView mIvMainMyPage;
    private ImageView mIvMainSearch;
    private ImageView mIvMainCart;
    private ImageView mIvMainSetting;
    private TextView mTvMainHome;
    private TextView mTvMainMyPage;
    private TextView mTvMainMyPageTopTitle;
    private FloatingActionButton mFloatingBtnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
        this.setFragment();
    }

    public void initialize() {

        mIvMainHome = findViewById(R.id.iv_main_home);
        mIvMainMyPage = findViewById(R.id.iv_main_mypage);
        mTvMainHome = findViewById(R.id.tv_main_home);
        mTvMainMyPage = findViewById(R.id.tv_main_mypage);
        mFloatingBtnMain = findViewById(R.id.fbtn_main);
        mTvMainMyPageTopTitle = findViewById(R.id.tv_main_top_title);

        mIvMainSearch = findViewById(R.id.iv_main_search);
        mIvMainCart = findViewById(R.id.iv_main_cart);
        mIvMainSetting = findViewById(R.id.iv_main_setting);

    }

    public void onClick(View view) {

        mTransaction = mFragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.iv_main_search:
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
                mTvMainMyPageTopTitle.setText(getString(R.string.oda));
                mIvMainSetting.setVisibility(View.GONE);
                mIvMainSearch.setVisibility(View.VISIBLE);
                mIvMainCart.setVisibility(View.VISIBLE);
                break;
            case R.id.linear_mypage:
                mTransaction.replace(R.id.frame_main, mFragmentMyPage).commitAllowingStateLoss();
                mIvMainHome.setImageResource(R.drawable.ic_home_normal);
                mIvMainMyPage.setImageResource(R.drawable.ic_my_page);
                mTvMainHome.setTextColor(getResources().getColor(R.color.normal));
                mTvMainMyPage.setTextColor(getResources().getColor(R.color.splash_back));
                mFloatingBtnMain.hide();
                mTvMainMyPageTopTitle.setText(getString(R.string.menu_myPage));
                mIvMainSetting.setVisibility(View.VISIBLE);
                mIvMainSearch.setVisibility(View.GONE);
                mIvMainCart.setVisibility(View.GONE);
                break;
            case R.id.iv_main_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.iv_main_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.fbtn_main:
                postDirectOrder();
                break;
        }
    }

    public void setFragment() {

        mFragmentManager = getSupportFragmentManager();
        mFragmentHome = new FragmentHome();
        mFragmentMyPage = new Fragment_mypage();
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.frame_main, mFragmentHome).commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplication(), LoginActivity.class));
    }

    //바로 주문
    void postDirectOrder(){


    }

    @Override
    public void postDirectOrderSuccess(String message) {

    }

    @Override
    public void postDirectOrderFailure(String message) {

    }
}
