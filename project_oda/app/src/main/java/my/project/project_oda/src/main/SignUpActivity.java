package my.project.project_oda.src.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import my.project.project_oda.R;
import my.project.project_oda.src.main.interfaces.SignUpActivityView;
import my.project.project_oda.src.main.models.SignUpForm;
import my.project.project_oda.src.BaseActivity;
import static my.project.project_oda.src.ApplicationClass.*;

public class SignUpActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, SignUpActivityView {

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

    SignUpForm form;
    int catering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.view_initialize();
        this.setListener();

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

        medt_id = findViewById(R.id.edt_sign_up_id);
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
                if(medt_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "사용할 아이디를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else duplicateCheck();
                break;
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.btn_verify:
                mbtn_verify.setBackgroundResource(R.drawable.btn_duplicate_ok);
                mbtn_verify.setText(getResources().getString(R.string.btn_verify_ok));
                mbtn_verify.setTextColor(getResources().getColor(R.color.white));
                mbtn_verify.setClickable(false);
                medt_business_number.setBackground(null);
                medt_post_detail.requestFocus();
                break;
            case R.id.btn_post:
                break;
            case R.id.tv_sign_up:
                if(mRgLine1.getCheckedRadioButtonId() != -1){
                    catering = mRgLine1.indexOfChild(mRgLine1.findViewById(mRgLine1.getCheckedRadioButtonId()))+1;
                    Log.d(TAG, "Line1:"+catering);
                }else if(mRgLine2.getCheckedRadioButtonId() != -1){
                    catering = mRgLine2.indexOfChild(mRgLine2.findViewById(mRgLine2.getCheckedRadioButtonId())) + 6;
                    Log.d(TAG, "Line2:"+catering);
                }

                if(medt_sign_up_password.getText().toString().equals(medt_sign_up_password_ok.getText().toString())) {
                    signUp(catering);
                }else Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();

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

    private void duplicateCheck(){
        showProgressDialog();
        final MainService mainService = new MainService(this, medt_id.getText().toString());
        mainService.getTest();
    }

    private void signUp(int business){
        showProgressDialog();
        final MainService signUpService = new MainService(this, medt_id.getText().toString(), medt_sign_up_password_ok.getText().toString(),
                business, medt_post.getText().toString()+" "+medt_post_detail.getText().toString() );
        signUpService.signUp();
    }

    //중복확인 성공, 실패
    @Override
    public void DuplicateSuccess(int code,String text) {
        hideProgressDialog();
        if(code ==150){
        mbtn_duplicate.setBackgroundResource(R.drawable.btn_duplicate_ok);
        mbtn_duplicate.setText(getResources().getString(R.string.btn_duplicate_ok));
        mbtn_duplicate.setTextColor(getResources().getColor(R.color.white));
        mbtn_duplicate.setClickable(false);
        medt_id.clearFocus();
        medt_id.setBackground(null);
        //비밀번호 입력란으로 focus 이동
        medt_sign_up_password.requestFocus();
        showCustomToast2("사용가능한 ID 입니다.");
        }else if(code == 100){
            showCustomToast2("중복된 ID가 존재합니다.");
        }
    }

    @Override
    public void DuplicateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    //회원가입 성공, 실패
    @Override
    public void SignUpSuccess(String text) {
        showProgressDialog();
        showCustomToast2(text);
        finish();
    }

    @Override
    public void SignUpFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void setListener(){

        medt_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mbtn_duplicate.setBackgroundResource(R.drawable.btn_duplicate);
                mbtn_duplicate.setText(getResources().getString(R.string.btn_duplicate));
                mbtn_duplicate.setTextColor(getResources().getColor(R.color.normal));
                mbtn_duplicate.setClickable(true);
            }
        });

        medt_sign_up_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    medt_sign_up_password.setBackground(null);
                    medt_sign_up_password_ok.requestFocus();
                }
                return false;
            }
        });

        medt_sign_up_password_ok.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    medt_sign_up_password_ok.setBackground(null);
                    medt_business_number.requestFocus();
                }
                return false;
            }
        });

        medt_post_detail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    medt_post_detail.setBackground(null);
                    medt_post_detail.clearFocus();
                }
                return false;
            }
        });
    }

}//SignUpActivity finished
