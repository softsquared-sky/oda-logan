package my.project.project_oda.src.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static my.project.project_oda.src.ApplicationClass.*;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.cart.models.CartItem;
import my.project.project_oda.src.order.adapter.OrderAdapter;
import my.project.project_oda.src.order.interfaces.OrderActivityView;
import my.project.project_oda.src.order.models.OrderItem;
import my.project.project_oda.src.order.orderComplete.OrderCompleteActivity;

public class OrderActivity extends BaseActivity implements OrderActivityView {

    private EditText mEdtOrderName;
    private EditText mEdtOrderAddress;
    private EditText mEdtOrderAddressDetail;
    private EditText mEdtOrderPhoneStart;
    private EditText mEdtOrderPhoneMiddle;
    private EditText mEdtOrderPhoneEnd;
    private EditText mEdtOrderMessage;

    private TextView mTvOrderName;
    private TextView mTvOrderAddress;
    private TextView mTvOrderAddressDetail;
    private TextView mTvOrderPhone;
    private TextView mTvOrderMessage;
    private TextView mTvOrderEstimatedPrice;
    private TextView mTvOrderTotalPrice;
    private RadioGroup mRgOrder;

    private ArrayList<OrderItem> mOrderList;
    private RecyclerView mRvOrder;
    private OrderAdapter mOrderAdapter;

    private int mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.initialize();
        this.getItemList();
        this.setListener();

    }//onCreate finished

    public void initialize() {

        mOrderList = new ArrayList<>();

        mRgOrder = findViewById(R.id.rg_order);

        mEdtOrderName = findViewById(R.id.edt_order_request_name);
        mEdtOrderAddress = findViewById(R.id.edt_order_request_address);
        mEdtOrderAddressDetail = findViewById(R.id.edt_order_request_address_detail);
        mEdtOrderPhoneStart = findViewById(R.id.edt_order_request_phone_1);
        mEdtOrderPhoneMiddle = findViewById(R.id.edt_order_request_phone_2);
        mEdtOrderPhoneEnd = findViewById(R.id.edt_order_request_phone_3);
        mEdtOrderMessage = findViewById(R.id.edt_order_request_message);

        mTvOrderName = findViewById(R.id.tv_order_border_name);
        mTvOrderAddress = findViewById(R.id.tv_order_border_address);
        mTvOrderAddressDetail = findViewById(R.id.tv_order_border_address_detail);
        mTvOrderPhone = findViewById(R.id.tv_order_border_phone);
        mTvOrderMessage = findViewById(R.id.tv_order_border_message);
        mTvOrderEstimatedPrice = findViewById(R.id.tv_order_request_estimated_price);
        mTvOrderTotalPrice = findViewById(R.id.tv_order_request_total_price);

        mRvOrder = findViewById(R.id.rv_order);
        mRvOrder.setLayoutManager(new LinearLayoutManager(this));
        mOrderAdapter = new OrderAdapter(this, mOrderList);
        mRvOrder.setAdapter(mOrderAdapter);

    }//initialzie finished

    public void getItemList() {

        boolean fromBasket = sSharedPreferences.getBoolean("fromBasket", false);
        ArrayList<CartItem> basketList = new ArrayList<>();
        OrderItem orderItem;

        if (fromBasket) {
            //Log.d(TAG, "장바구니에서 옴");
            Gson gson = new Gson();
            String json = sSharedPreferences.getString("basket", "");
            Type type = new TypeToken<ArrayList<CartItem>>() {
            }.getType();
            if (gson.fromJson(json, type) != null) {
                basketList = gson.fromJson(json, type);
            }

            for (int i = 0; i < basketList.size(); i++) {
                OrderItem orderItemFromBasket = new OrderItem(basketList.get(i).getpNum(), basketList.get(i).getpName(),
                        basketList.get(i).getPrice(), basketList.get(i).getImage(), basketList.get(i).getCount());
                mOrderList.add(orderItemFromBasket);
            }

            int price = 0;
            for (int i = 0; i < mOrderList.size(); i++) {
                int productTotal = mOrderList.get(i).getOrderPrice() * mOrderList.get(i).getOrderNumOfProduct();
                price += productTotal;
            }

            String p = myFormatter.format(price).concat("원");
            mTvOrderEstimatedPrice.setText(p);
            mTvOrderTotalPrice.setText(p);

        } else {
            //Log.d(TAG, "상품상세에서 옴");
            Intent intent = getIntent();
            OrderItem item = new OrderItem(intent.getExtras().getInt("pNum"), intent.getExtras().getString("title"),
                    intent.getExtras().getInt("price"), intent.getExtras().getString("image")
                    , intent.getExtras().getInt("itemCount"));

            mOrderList.add(item);
            int estimatePrice = intent.getExtras().getInt("price") * item.getOrderNumOfProduct();
            String price = myFormatter.format(estimatePrice).concat("원");
            mTvOrderEstimatedPrice.setText(price);
            mTvOrderTotalPrice.setText(price);

        }

        mOrderAdapter.notifyDataSetChanged();

    }

    public void setListener() {

        mEdtOrderName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderName.setTextColor(getResources().getColor(R.color.splash_back));
                    mTvOrderName.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderName.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderName.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderAddress.setTextColor(getResources().getColor(R.color.splash_back));
                    mTvOrderAddress.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderAddress.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderAddress.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderAddressDetail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderAddressDetail.setTextColor(getResources().getColor(R.color.splash_back));
                    mTvOrderAddressDetail.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderAddressDetail.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderAddressDetail.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderPhoneStart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderPhoneStart.setTextColor(getResources().getColor(R.color.splash_back));
                    mEdtOrderPhoneStart.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    mTvOrderPhone.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderPhoneStart.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderPhone.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderPhoneMiddle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderPhoneMiddle.setTextColor(getResources().getColor(R.color.splash_back));
                    mTvOrderPhone.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderPhoneMiddle.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderPhone.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderPhoneEnd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderPhoneEnd.setTextColor(getResources().getColor(R.color.splash_back));
                    mTvOrderPhone.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderPhoneEnd.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderPhone.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderPhoneStart.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mEdtOrderPhoneMiddle.requestFocus();
                } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mEdtOrderPhoneMiddle.requestFocus();
                }
                return false;
            }
        });

        mEdtOrderPhoneMiddle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mEdtOrderPhoneEnd.requestFocus();
                } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mEdtOrderPhoneEnd.requestFocus();
                }
                return false;
            }
        });

        mEdtOrderPhoneEnd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mEdtOrderMessage.requestFocus();
                } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mEdtOrderMessage.requestFocus();
                }
                return false;
            }
        });

        mEdtOrderMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEdtOrderMessage.setTextColor(getResources().getColor(R.color.splash_back));
                    mTvOrderMessage.setBackgroundColor(getResources().getColor(R.color.splash_back));
                } else {
                    mEdtOrderMessage.setTextColor(getResources().getColor(R.color.review_write_color));
                    mTvOrderMessage.setBackgroundColor(getResources().getColor(R.color.boarder));
                }
            }
        });

        mEdtOrderMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoard(mEdtOrderMessage);
                    mEdtOrderMessage.clearFocus();
                    TextView tvOrderProductInfo = findViewById(R.id.tv_order_product_info);
                    tvOrderProductInfo.requestFocus();
                }
                return false;
            }
        });

        final TextView TvOrderTime = findViewById(R.id.tv_order_time);
        RadioGroup OrderRadioGroup = findViewById(R.id.rg_order);
        OrderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb_order_morning:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
                        mTime = 1;
                        break;
                    case R.id.rb_order_am:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
                        mTime = 2;
                        break;
                    case R.id.rb_order_pm:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
                        mTime = 3;
                        break;
                    case R.id.rb_order_dawn:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
                        mTime = 4;
                        break;
                }
            }
        });


    }//setListener finished

    private void hideKeyBoard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_order_back:
                finish();
                break;
            case R.id.tv_order_request:
                postOrderRequest();
                break;
        }
    }//onClick finished

    void postOrderRequest() {

        JSONObject params = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < mOrderList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("pNum", mOrderList.get(i).getpNum());
                jsonObject.put("amount", mOrderList.get(i).getOrderNumOfProduct());
                jsonArray.put(jsonObject);
            }
            params.put("payList", jsonArray);
        } catch (JSONException e) {
            return;
        }

        showProgressDialog();
        final OrderService orderService = new OrderService(this, params);
        orderService.postOrderRequest();

    }


    @Override
    public void postOrderRequestSuccess(String message) {
        hideProgressDialog();
        Intent intent = new Intent(this, OrderCompleteActivity.class);
        intent.putExtra("firstProduct", mOrderList.get(0).getOrderTitle());
        intent.putExtra("price", mTvOrderTotalPrice.getText().toString());
        intent.putExtra("productsNum", mOrderList.size() - 1);
        intent.putExtra("address", mEdtOrderAddress.getText().toString());
        intent.putExtra("time", mTime);
        startActivity(intent);
    }

    @Override
    public void postOrderRequestFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}//OrderActivity finished
