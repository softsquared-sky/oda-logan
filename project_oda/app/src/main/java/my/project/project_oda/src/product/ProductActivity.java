package my.project.project_oda.src.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.HeightWrappingViewPager;
import my.project.project_oda.src.product.fragments.*;
import my.project.project_oda.src.product.adapters.SectionAdapter;
import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.models.ProductDetailResponse;
import my.project.project_oda.src.search.SearchActivity;

import static my.project.project_oda.src.ApplicationClass.*;

public class ProductActivity extends BaseActivity implements ViewPager.OnPageChangeListener, ProductActivityView {

    Context mContext;
   // private ViewPager mViewPager;
    private HeightWrappingViewPager mViewPager;
    SectionAdapter madapter = new SectionAdapter(getSupportFragmentManager());
    fragmentProductDetail mFragmentProductDetail;
    fragmentProductReview mFragmentProductReview;
    fragmentProductQnA mFragmentProductQnA;
    TabLayout mTabLayout;

    ImageView mIvProductMainImage;
    TextView mTvProductTitle;
    TextView mTvProductPrice;
    Intent intent;
    ProductDetailResponse mResponse;
    String qpp;
    String storeMethod;
    String origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        this.initialize();
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
        }
    }

    public void initialize() {

        mContext = this;
        mFragmentProductReview = new fragmentProductReview(mContext);
        mFragmentProductQnA = new fragmentProductQnA(mContext);
        mViewPager = findViewById(R.id.product_container);

        intent = getIntent();
        String title = intent.getExtras().getString("title");
        int price = intent.getExtras().getInt("price");
        String imageUrl = intent.getExtras().getString("url");
        mIvProductMainImage = findViewById(R.id.iv_product_main);
        mTvProductTitle = findViewById(R.id.tv_product_title);
        mTvProductPrice = findViewById(R.id.tv_product_price);

        Glide.with(mContext).load(imageUrl).into(mIvProductMainImage);
        mTvProductTitle.setText(title);
        mTvProductPrice.setText(myFormatter.format(price) + "원");

    }

    public void setupViewPager(ViewPager viewPager) {
        madapter.addFragment(mFragmentProductDetail, getString(R.string.product_detail));
        madapter.addFragment(new fragmentProductReview(mContext), getString(R.string.product_review));
        madapter.addFragment(new fragmentProductQnA(mContext), getString(R.string.product_QnA));
        viewPager.setAdapter(madapter);
        viewPager.measure(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                break;
        }
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
    public void getProductDetailSuccess(JsonObject jsonObject) {
        hideProgressDialog();
        //mFragmentProductDetail = new fragmentProductDetail(mContext, response);
        JsonArray contentArray = jsonObject.getAsJsonArray("detailContent");
        qpp = contentArray.get(0).getAsJsonObject().get("qpp").getAsString();
        storeMethod = contentArray.get(0).getAsJsonObject().get("storeMethod").getAsString();
        origin = contentArray.get(0).getAsJsonObject().get("origin").getAsString();

        JsonArray resultArray = jsonObject.getAsJsonArray("imageResult");
        int arraySize = resultArray.size();

        mFragmentProductDetail = new fragmentProductDetail(mContext);
        Bundle bundle = new Bundle(4 + arraySize);
        bundle.putString("qpp", qpp);
        bundle.putString("storeMethod", storeMethod);
        bundle.putString("origin", origin);
        bundle.putInt("size", arraySize);

        for (int i = 0; i < arraySize; i++) {
            bundle.putString(i + "image", resultArray.get(i).getAsJsonObject().get("imageUrl").getAsString());
        }

        mFragmentProductDetail.setArguments(bundle);
        setupViewPager(mViewPager);
    }

    @Override
    public void getProductDetailFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}

