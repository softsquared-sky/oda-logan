package my.project.project_oda.src.order.orderComplete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.main.home.interfaces.HomeActivityView;

public class OrderCompleteActivity extends BaseActivity {

    private TextView mTvCompleteTitle;
    private TextView mTvCompletePrice;
    private TextView mTvCompleteAddress;
    private TextView mTvCompleteTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_request_complete);
        this.initialize();

    }//onCreate finished

    public void initialize() {

        mTvCompleteTitle = findViewById(R.id.tv_order_complete_title);
        mTvCompletePrice = findViewById(R.id.tv_order_complete_price);
        mTvCompleteAddress = findViewById(R.id.tv_order_complete_address);
        mTvCompleteTime = findViewById(R.id.tv_order_complete_time);

        Intent intent = getIntent();
        int time = intent.getExtras().getInt("time");

        if(intent.getExtras().getInt("productsNum") != 0) {
            mTvCompleteTitle.setText(intent.getExtras().getString("firstProduct").concat(" 외 ").
                    concat(String.valueOf(intent.getExtras().getInt("productsNum"))).concat("개의 상품"));
            mTvCompletePrice.setText(getString(R.string.complete_price).concat(" ").concat(intent.getExtras().getString("price")));
            mTvCompleteAddress.setText(intent.getExtras().getString("address"));
        }else{
            mTvCompleteTitle.setText(intent.getExtras().getString("firstProduct"));
            mTvCompletePrice.setText(getString(R.string.complete_price).concat(" ").concat(intent.getExtras().getString("price")));
            mTvCompleteAddress.setText(intent.getExtras().getString("address"));
        }

        if(time == 1){
            mTvCompleteTime.setText(getString(R.string.complete_morning));
        }else if(time == 2){
            mTvCompleteTime.setText(getString(R.string.complete_am));
        }else if(time == 3){
            mTvCompleteTime.setText(getString(R.string.complete_pm));
        }else if(time == 4){
            mTvCompleteTime.setText(getString(R.string.complete_dawn));
        }

//        mTvCompleteTime.setText(getString(R.string.complete_pm));

    }//initialzie finished

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_order_complete_close:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}//OrderActivity finished
