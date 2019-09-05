package my.project.project_oda.src.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.cart.CartActivity;
import my.project.project_oda.src.product.detail.fragmentProductDetail;
import my.project.project_oda.src.product.qna.fragmentProductQnA;
import my.project.project_oda.src.product.adapters.SectionAdapter;
import my.project.project_oda.src.product.review.fragmentProductReview;
import my.project.project_oda.src.search.SearchActivity;

import static my.project.project_oda.src.ApplicationClass.*;

public class ProductActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    Context mContext;
    // private ViewPager mViewPager;
    private ViewPager mViewPager;
    SectionAdapter mAdapter = new SectionAdapter(getSupportFragmentManager());
    fragmentProductDetail mFragmentProductDetail;
    fragmentProductReview mFragmentProductReview;
    fragmentProductQnA mFragmentProductQnA;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        this.initialize();

        mTabLayout = findViewById(R.id.product_tab);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_product_back:
                finish();
                break;
            case R.id.iv_product_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.iv_product_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
        }
    }

    public void initialize() {

        mContext = this;
        mFragmentProductDetail = new fragmentProductDetail(mContext, getIntent().getExtras().getInt("productNumber"));
        mFragmentProductReview = new fragmentProductReview(mContext, getIntent().getExtras().getInt("productNumber"));
        mFragmentProductQnA = new fragmentProductQnA(mContext);
        mViewPager = findViewById(R.id.product_container);
        setupViewPager(mViewPager);
    }

    public void updateViewPagerSize(int position) {
        View view = mViewPager.getChildAt(position);
        view.measure(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, view.getMeasuredHeight());
        mViewPager.setLayoutParams(params);
    }

    public void setupViewPager(ViewPager viewPager) {
        mAdapter.addFragment(mFragmentProductDetail, getString(R.string.product_detail));
        mAdapter.addFragment(mFragmentProductReview, getString(R.string.product_review));
        mAdapter.addFragment(mFragmentProductQnA, getString(R.string.product_QnA));
        viewPager.setAdapter(mAdapter);
        viewPager.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}

