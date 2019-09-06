package my.project.project_oda.src.order;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.order.adapter.OrderAdapter;
import my.project.project_oda.src.order.interfaces.OrderActivityView;
import my.project.project_oda.src.order.models.OrderItem;

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

    private ArrayList<OrderItem> mOrderList;
    private RecyclerView mRvOrder;
    private OrderAdapter mOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.initialize();
        this.setListener();

    }//onCreate finished

    public void initialize() {

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

        mRvOrder = findViewById(R.id.rv_order);
        mOrderAdapter = new OrderAdapter(this, mOrderList);
        mRvOrder.setAdapter(mOrderAdapter);

    }//initialzie finished

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
                        break;
                    case R.id.rb_order_am:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
                        break;
                    case R.id.rb_order_pm:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
                        break;
                    case R.id.rb_order_dawn:
                        TvOrderTime.setTextColor(getResources().getColor(R.color.catering));
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
        }
    }//onClick finished

    void postOrderRequest() {

        JSONObject params = new JSONObject();
        try{

        }catch (Exception e){

        }

        final OrderService orderService = new OrderService(this, params);
        orderService.postOrderRequest();

    }


    @Override
    public void postOrderRequestSuccess(String message) {

    }

    @Override
    public void postOrderRequestFailure(String message) {

    }
}//OrderActivity finished
