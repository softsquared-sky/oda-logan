package my.project.project_oda.src.cart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.cart.adapter.CartAdapter;
import my.project.project_oda.src.cart.models.CartItem;

import static my.project.project_oda.src.ApplicationClass.sSharedPreferences;


public class CartActivity extends BaseActivity {

    private CheckBox mChboxSelectAll;
    private TextView mTvSelectAll;
    private TextView mTvOrderRequest;
    private RecyclerView mRvCart;
    private LinearLayout mLinearEmpty;
    ArrayList<CartItem> mCartList;
    CartAdapter mCartAdapter;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.initialize();
    }

    public void initialize() {

        mCartList = new ArrayList<>();
        mCartAdapter = new CartAdapter(this, mCartList);
        mEditor = sSharedPreferences.edit();
        mChboxSelectAll = findViewById(R.id.chbox_cart_select_all);
        mTvSelectAll = findViewById(R.id.tv_cart_select_all);
        mTvOrderRequest = findViewById(R.id.tv_cart_order_request);
        mLinearEmpty = findViewById(R.id.linear_cart_init);
        mRvCart = findViewById(R.id.rv_cart);
        mRvCart.setLayoutManager(new LinearLayoutManager(this));
        mRvCart.setAdapter(mCartAdapter);

        mCartList.add(new CartItem(1, "경기도 농장직배송 특란 1판 30구", "","3,750원", "300"));
        mCartAdapter.notifyDataSetChanged();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cart_back:
                finish();
                break;
            case R.id.linear_cart_init:
                mLinearEmpty.setVisibility(View.GONE);
                mChboxSelectAll.setClickable(true);
                mTvSelectAll.setTextColor(getResources().getColor(R.color.catering));
                mTvOrderRequest.setBackgroundColor(getResources().getColor(R.color.splash_back));
                break;
        }
    }

}
