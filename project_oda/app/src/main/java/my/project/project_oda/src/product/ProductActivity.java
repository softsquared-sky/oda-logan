package my.project.project_oda.src.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.HeightWrappingViewPager;
import my.project.project_oda.src.cart.CartActivity;
import my.project.project_oda.src.product.fragments.*;
import my.project.project_oda.src.product.adapters.SectionAdapter;
import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.models.ProductDetailResponse;
import my.project.project_oda.src.product.models.Result;
import my.project.project_oda.src.product.models.ReviewResult;
import my.project.project_oda.src.product.models.imageResult;
import my.project.project_oda.src.search.SearchActivity;

import static my.project.project_oda.src.ApplicationClass.*;

public class ProductActivity extends BaseActivity implements ViewPager.OnPageChangeListener, ProductActivityView {

    Context mContext;
    // private ViewPager mViewPager;
    private ViewPager mViewPager;
    SectionAdapter mAdapter = new SectionAdapter(getSupportFragmentManager());
    fragmentProductDetail mFragmentProductDetail;
    fragmentProductReview mFragmentProductReview;
    fragmentProductQnA mFragmentProductQnA;
    TabLayout mTabLayout;

    ImageView mIvProductMainImage;
    TextView mTvProductTitle;
    TextView mTvProductPrice;
    Intent intent;

    String mQpp;
    String mStoreMethod;
    String mOrigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        this.initialize();
        this.getProductReview();
        this.getProductDetail();
        //setupViewPager(mViewPager);

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
        mFragmentProductQnA = new fragmentProductQnA(mContext);
        mViewPager = findViewById(R.id.product_container);

        intent = getIntent();
        //intent null 체크
        if (intent == null) {
            //인텐트를 제대로 못 받았으면 다시 돌아감, warning
            return;
        }

        //getExtras도 null check해야함
        if(intent.getExtras() == null){
            return;
        }

        String title = intent.getExtras().getString("title");
        int price = intent.getExtras().getInt("price");
        String imageUrl = intent.getExtras().getString("url");

        mIvProductMainImage = findViewById(R.id.iv_product_main);
        mTvProductTitle = findViewById(R.id.tv_product_title);
        mTvProductPrice = findViewById(R.id.tv_product_price);

        Glide.with(mContext).load(imageUrl).placeholder(R.drawable.ic_logo).into(mIvProductMainImage);
        mTvProductTitle.setText(title);
        //concat생활화
        mTvProductPrice.setText(myFormatter.format(price).concat("원"));

    }

    public void updateViewPagerSize(int position) {
        View view = mViewPager.getChildAt(position);
        view.measure(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, view.getMeasuredHeight());
        mViewPager.setLayoutParams(params);
    }

    public void setupViewPager(ViewPager viewPager) {
        mAdapter.addFragment(mFragmentProductDetail, getString(R.string.product_detail));
        mAdapter.addFragment(new fragmentProductReview(mContext), getString(R.string.product_review));
        mAdapter.addFragment(new fragmentProductQnA(mContext), getString(R.string.product_QnA));
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

    //네트워크 연결 부분

    private void getProductDetail() {
        showProgressDialog();
        final ProductService productService = new ProductService(this, 1);
        productService.getProductDetail();
    }

    @Override
    public void getProductDetailSuccess(Result result) {
        hideProgressDialog();
        //mFragmentProductDetail = new fragmentProductDetail(mContext, response);

        mQpp = result.getQpp();
        mStoreMethod = result.getStoreMethod();
        mOrigin = result.getOrigin();

        List<imageResult> images = result.getImageResult();

        mFragmentProductDetail = new fragmentProductDetail(mContext);
        Bundle bundle = new Bundle(5);
        bundle.putString("qpp", mQpp);
        bundle.putString("storeMethod", mStoreMethod);
        bundle.putString("origin", mOrigin);
        bundle.putString("image1", images.get(0).getImageUrl());
        bundle.putString("image2", images.get(1).getImageUrl());
        Log.d(TAG, "url 테스트: ".concat(images.get(0).getImageUrl()));
        mFragmentProductDetail.setArguments(bundle);
        setupViewPager(mViewPager);
    }

    @Override
    public void getProductDetailFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    private void getProductReview() {
        showProgressDialog();
        final ProductService productService = new ProductService(this, 1);
        productService.getProductReview();
    }

    @Override
    public void getReviewSuccess(List<ReviewResult> result) {
        hideProgressDialog();
        mFragmentProductReview = new fragmentProductReview(mContext);
        Bundle bundle = new Bundle();

    }

    @Override
    public void getReviewFailure(String message) {
        hideProgressDialog();
    }
}

