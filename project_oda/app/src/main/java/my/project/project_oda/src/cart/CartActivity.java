package my.project.project_oda.src.cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.cart.adapter.CartAdapter;
import my.project.project_oda.src.cart.interfaces.CartActivityView;
import my.project.project_oda.src.cart.models.CartItem;
import my.project.project_oda.src.order.OrderActivity;

import static my.project.project_oda.src.ApplicationClass.myFormatter;
import static my.project.project_oda.src.ApplicationClass.sSharedPreferences;


public class CartActivity extends BaseActivity implements CartActivityView {

    private CheckBox mChboxSelectAll;
    private TextView mTvSelectAll;
    private TextView mTvOrderRequest;
    private TextView mTvTotalPrice;
    private TextView mTvSelectedPrice;
    private RecyclerView mRvDirectOrder;
    private RecyclerView mRvCart;
    private LinearLayout mLinearEmpty;
    private LinearLayout mLinearDirectOrder;
    private LinearLayout mLinearBasket;
    ArrayList<CartItem> mCartList;
    ArrayList<CartItem> mDirectOrderList;
    ArrayList<CartItem> mBasketList;
    CartAdapter mCartAdapter;
    CartAdapter mDirectOrderAdapter;
    boolean mIsProduct;
    int mTotalPrice;
    int mSelectedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.initialize();
        this.getBasket();
    }

    public void initialize() {

        mCartList = new ArrayList<>();
        mDirectOrderList = new ArrayList<>();
        mBasketList = new ArrayList<>();

        mCartAdapter = new CartAdapter(this, mCartList, this, true);
        mDirectOrderAdapter = new CartAdapter(this, mDirectOrderList, this, false);

        mChboxSelectAll = findViewById(R.id.chbox_cart_select_all);
        mTvSelectAll = findViewById(R.id.tv_cart_select_all);
        mTvOrderRequest = findViewById(R.id.tv_cart_order_request);
        mTvTotalPrice = findViewById(R.id.tv_cart_total_price);
        mTvSelectedPrice = findViewById(R.id.tv_cart_selected_price);

        mLinearEmpty = findViewById(R.id.linear_cart_init);
        mLinearBasket = findViewById(R.id.linear_cart_basket);
        mLinearDirectOrder = findViewById(R.id.linear_cart_direct_order);

        mRvCart = findViewById(R.id.rv_cart);
        mRvCart.setLayoutManager(new LinearLayoutManager(this));
        mRvCart.setAdapter(mCartAdapter);

        mRvDirectOrder = findViewById(R.id.rv_direct_order);
        mRvDirectOrder.setLayoutManager(new LinearLayoutManager(this));
        mRvDirectOrder.setAdapter(mDirectOrderAdapter);

        mCartAdapter.notifyDataSetChanged();

        mTotalPrice = 0;
        mSelectedPrice = 0;
        mIsProduct = false;

        mChboxSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChboxSelectAll.isChecked()) {

                    mSelectedPrice = 0;

                    for (int i = 0; i < mCartList.size(); i++) {
                        mCartList.get(i).setChecked(true);
                        mSelectedPrice += mCartList.get(i).getPrice() * mCartList.get(i).getCount();
                    }

                    for (int i = 0; i < mDirectOrderList.size(); i++) {
                        mDirectOrderList.get(i).setChecked(true);
                        mSelectedPrice += mDirectOrderList.get(i).getPrice() * mDirectOrderList.get(i).getCount();
                    }

                    mCartAdapter.notifyDataSetChanged();
                    mDirectOrderAdapter.notifyDataSetChanged();
                    setSelectedPrice(mSelectedPrice);

                } else {

                    for (int i = 0; i < mCartList.size(); i++) {
                        mCartList.get(i).setChecked(false);
                    }

                    for (int i = 0; i < mDirectOrderList.size(); i++) {
                        mDirectOrderList.get(i).setChecked(false);
                    }

                    mCartAdapter.notifyDataSetChanged();
                    mDirectOrderAdapter.notifyDataSetChanged();

                    setSelectedPrice(0);

                }
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cart_back:
                finish();
                break;
            case R.id.tv_cart_order_request:
                //물품이 장바구니에 있을경우
                if (mIsProduct) {
                    //장바구니 목록들 저장
                    ArrayList<CartItem> temp = new ArrayList<>();

                    //체크된 아이템 temp에 저장
                    for (int i = 0; i < mBasketList.size(); i++) {
                        if (mBasketList.get(i).isChecked()) {
                            if(mBasketList.get(i).getCount() != 0) {
                                temp.add(mBasketList.get(i));
                            }
                        }
                    }

                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    String json = gson.toJson(temp);
                    editor.putString("basket", json);
                    editor.putBoolean("fromBasket", true);
                    editor.apply();

                    boolean numberCheck = false;
                    for(int i=0; i<temp.size(); i++){
                        if(temp.get(i).getCount() != 0){
                            numberCheck = true;
                            break;
                        }
                    }
                    if(numberCheck) {
                        startActivity(new Intent(this, OrderActivity.class));
                    }else {
                        showCustomToast(getString(R.string.cart_select_at_least));
                    }
                }
                break;
        }
    }

    public void setTotalPricePlus(int price) {
        mTotalPrice += price;
        mTvTotalPrice.setText(myFormatter.format(mTotalPrice).concat("원"));
    }

    public void setTotalPrice(int price) {
        mTotalPrice = price;
        mTvTotalPrice.setText(myFormatter.format(mTotalPrice).concat("원"));
    }

    public void setTotalPriceMinus(int price) {
        mTotalPrice -= price;
        mTvTotalPrice.setText(myFormatter.format(mTotalPrice).concat("원"));
    }

    public void setSelectedPricePlus(int price) {
        mSelectedPrice += price;
        mTvSelectedPrice.setText(myFormatter.format(mSelectedPrice).concat("원"));
    }

    public void setSelectedPrice(int price) {
        mSelectedPrice = price;
        mTvSelectedPrice.setText(myFormatter.format(mSelectedPrice).concat("원"));
    }

    public void setSelectedPriceMinus(int price) {
        mSelectedPrice -= price;
        mTvSelectedPrice.setText(myFormatter.format(mSelectedPrice).concat("원"));
    }

    public void hideDirectOrder() {
        mRvDirectOrder.setVisibility(View.GONE);
        mLinearDirectOrder.setVisibility(View.GONE);
    }

    public void hideBasket() {
        mRvCart.setVisibility(View.GONE);
        mLinearBasket.setVisibility(View.GONE);
    }

    private void getBasket() {
        showProgressDialog();
        final CartService cartService = new CartService(this);
        cartService.getBasket();
    }

    @Override
    public void getBasketSuccess(List<CartItem> basketList, int code) {

        //장바구니에 물품이 있다면  950: 장바구니 물품 없음
        if (code != 950) {
            mLinearEmpty.setVisibility(View.GONE);
            mChboxSelectAll.setClickable(true);
            mTvSelectAll.setTextColor(getResources().getColor(R.color.catering));
            mTvOrderRequest.setBackgroundColor(getResources().getColor(R.color.splash_back));
            mIsProduct = true;

            for (int i = 0; i < basketList.size(); i++) {
                //바로주문 상품일 경우
                if (basketList.get(i).getType().equals(getResources().getString(R.string.cart_direct_order))) {
                    mDirectOrderList.add(basketList.get(i));
                    mLinearDirectOrder.setVisibility(View.VISIBLE);
                    mRvDirectOrder.setVisibility(View.VISIBLE);
                    mBasketList.add(basketList.get(i));
                    //장바구니 상품일 경우
                } else if (basketList.get(i).getType().equals(getResources().getString(R.string.cart))) {
                    mCartList.add(basketList.get(i));
                    mBasketList.add(basketList.get(i));
                }
            }

            mCartAdapter.notifyDataSetChanged();
            mDirectOrderAdapter.notifyDataSetChanged();

        }
        hideProgressDialog();

    }

    @Override
    public void getBasketFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}
