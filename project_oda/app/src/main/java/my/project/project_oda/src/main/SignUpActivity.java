package my.project.project_oda.src.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import my.project.project_oda.R;
import my.project.project_oda.src.main.models.SignUpForm;

public class SignUpActivity extends Activity implements RadioGroup.OnCheckedChangeListener {


    private RadioButton mRb[];
    private RadioGroup mRgLine1;
    private RadioGroup mRgLine2;
    private int[] RADIOID = {R.id.rb_0, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4, R.id.rb_5, R.id.rb_7, R.id.rb_7};

    private EditText medt_id;
    private EditText medt_sign_up_password;
    private EditText medt_sign_up_password_ok;
    private EditText medt_business_number;
    private EditText medt_post;
    private EditText medt_post_detail;

    private Button mbtn_duplicate;
    private Button mbtn_verify;
    private Button mbtn_post;

    private static String TAG = "로그";
    SignUpForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.view_initialize();

    }//onCreate finished

    public void view_initialize(){

        mRgLine1 = findViewById(R.id.rg_1);
        mRgLine1.clearCheck();
        mRgLine1.setOnCheckedChangeListener(this);


        mRgLine2 = findViewById(R.id.rg_2);
        mRgLine1.clearCheck();
        mRgLine2.setOnCheckedChangeListener(this);

        mRb = new RadioButton[8];
        for(int i =0; i< RADIOID.length; i++) {
            mRb[i] = findViewById(RADIOID[i]);
        }

        medt_id = findViewById(R.id.edt_id);
        medt_sign_up_password = findViewById(R.id.edt_sign_up_password);
        medt_sign_up_password_ok = findViewById(R.id.edt_sign_up_password_ok);
        medt_business_number = findViewById(R.id.edt_business_number);
        medt_post = findViewById(R.id.edt_post);
        medt_post_detail = findViewById(R.id.edt_post_detail);

        mbtn_duplicate = findViewById(R.id.btn_duplicate);
        mbtn_verify = findViewById(R.id.btn_verify);
        mbtn_post = findViewById(R.id.btn_post);

    }//view_initialize finished

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_duplicate:
                break;
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.btn_verify:
                break;
            case R.id.btn_post:
                break;
            case R.id.tv_sign_up:
                int catering;
                if(mRgLine1.getCheckedRadioButtonId() != -1){
                    catering = mRgLine1.indexOfChild(mRgLine1.findViewById(mRgLine1.getCheckedRadioButtonId()));
                    Log.d(TAG, "Line1:"+catering);
                }else if(mRgLine2.getCheckedRadioButtonId() != -1){
                    catering = mRgLine2.indexOfChild(mRgLine2.findViewById(mRgLine2.getCheckedRadioButtonId())) + 5;
                    Log.d(TAG, "Line2:"+catering);
                }
                finish();
                break;
        }
    }//onClick finished

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if(radioGroup == mRgLine1){
            if(checkedId != -1){
                mRgLine2.setOnCheckedChangeListener(null);
                mRgLine2.clearCheck();
                mRgLine2.setOnCheckedChangeListener(this);
            }
        }else if (radioGroup == mRgLine2){
            if(checkedId != -1) {
                mRgLine1.setOnCheckedChangeListener(null);
                mRgLine1.clearCheck();
                mRgLine1.setOnCheckedChangeListener(this);
            }
        }
    }

}//LoginActivity finished
