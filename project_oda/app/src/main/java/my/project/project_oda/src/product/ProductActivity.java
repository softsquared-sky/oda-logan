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

import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.cart.CartActivity;
import my.project.project_oda.src.order.OrderActivity;
import my.project.project_oda.src.product.detail.fragmentProductDetail;
import my.project.project_oda.src.product.interfaces.ProductActivityView;
import my.project.project_oda.src.product.qna.fragmentProductQnA;
import my.project.project_oda.src.product.adapters.SectionAdapter;
import my.project.project_oda.src.product.review.fragmentProductReview;
import my.project.project_oda.src.product.review.reviewPost.reviewPostActivity;
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
    TextView mTvProductPutCart;
    TextView mTvProductOrder;
    TextView mTvProductSelectNumber;
    TextView mTvProductSelectMinus;
    TextView mTvProductSelectPlus;
    TabLayout mTabLayout;
    LinearLayout mLinearProductSelectNum;
    boolean mOneClick;
    int mSelectedNumber;

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
            case R.id.tv_product_put_cart:
                if (!mOneClick) {
                    //처음 클릭 시, width 늘림, 수량 선택 바 보이기
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvProductPutCart.getLayoutParams();
                    params.weight = 0;
                    mTvProductPutCart.setLayoutParams(params);
                    mOneClick = true;
                } else {
                    //2번 클릭 시,
                    postBasket();
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvProductPutCart.getLayoutParams();
                    params.weight = 1;
                    mTvProductPutCart.setLayoutParams(params);
                    mOneClick = false;
                }
                break;
            case R.id.tv_product_order:
                if (!mOneClick) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvProductOrder.getLayoutParams();
                    params.weight = 0;
                    mTvProductOrder.setLayoutParams(params);
                    mLinearProductSelectNum.setVisibility(View.VISIBLE);
                    mOneClick = true;
                } else {
                    mOneClick = false;
                    Intent intent = new Intent(this, OrderActivity.class);
                    intent.putExtra("itemCount", mTvProductSelectNumber.getText().toString());
                    startActivity(intent);
                }
                break;
            case R.id.tv_product_select_plus:
                mSelectedNumber += 1;
                mTvProductSelectNumber.setText(String.valueOf(mSelectedNumber));
                break;
            case R.id.tv_product_select_minus:
                if (mSelectedNumber >= 2) {
                    mSelectedNumber -= 1;
                    mTvProductSelectNumber.setText(String.valueOf(mSelectedNumber));
                }
                break;
            case R.id.tv_review_write:
                startActivity(new Intent(this, reviewPostActivity.class));
                Log.d(TAG, "눌림");
                break;
        }
    }

    public void initialize() {

        mContext = this;
        mOneClick = false;
        mSelectedNumber = 1;
        mFragmentProductDetail = new fragmentProductDetail(mContext, getIntent().getExtras().getInt("productNumber"));
        mFragmentProductReview = new fragmentProductReview(mContext, getIntent().getExtras().getInt("productNumber"));
        mFragmentProductQnA = new fragmentProductQnA(mContext);
        mTvProductPutCart = findViewById(R.id.tv_product_put_cart);
        mTvProductOrder = findViewById(R.id.tv_product_order);
        mTvProductSelectMinus = findViewById(R.id.tv_product_select_minus);
        mTvProductSelectNumber = findViewById(R.id.tv_product_select_num);
        mTvProductSelectNumber.setText(String.valueOf(mSelectedNumber));
        mTvProductSelectPlus = findViewById(R.id.tv_product_select_plus);
        mViewPager = findViewById(R.id.product_container);
        mLinearProductSelectNum = findViewById(R.id.linear_product_select_num);
        setupViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        mAdapter.addFragment(mFragmentProductDetail, getString(R.string.product_detail));
        mAdapter.addFragment(mFragmentProductReview, getString(R.string.product_review));
        mAdapter.addFragment(mFragmentProductQnA, getString(R.string.product_QnA));
        viewPager.setAdapter(mAdapter);
        viewPager.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    //장바구니 담기
    void postBasket() {

        JSONObject params;
        try {
            params = new JSONObject();
            params.put("pNum", getIntent().getExtras().getInt("productNumber"));
        } catch (Exception e) {
            //Log.d(TAG, "error: " + e);
            return;
        }

        showProgressDialog();
        final ProductService productService = new ProductService(this, params);
        productService.postBasket();

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


    @Override
    public void getProductBasicSuccess(String message) {

    }

    @Override
    public void getProductBasicFailure(String message) {

    }

    @Override
    public void postBasketSuccess(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    @Override
    public void postBasketFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}

