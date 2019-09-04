package my.project.project_oda.src.signUp;

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
import org.json.JSONObject;
import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.signUp.interfaces.SignUpActivityView;

import static my.project.project_oda.src.ApplicationClass.TAG;

public class SignUpActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, SignUpActivityView {

    private RadioButton mRb[];
    private RadioGroup mRgLine1;
    private RadioGroup mRgLine2;
    private final int[] mRadioId = {R.id.rb_0, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4, R.id.rb_5, R.id.rb_7, R.id.rb_7};

    private EditText mEdtId;
    private EditText mEdtSignUpPassword;
    private EditText mEdtSignUpPasswordOk;
    private EditText mEdtBusinessNumber;
    private EditText mEdtPost;
    private EditText mEdtPostDetail;

    private Button mBtnDuplicate;
    private Button mBtnVerify;
    //private Button mBtnPost;
    //private TextView mTvSignUp;
    int mCatering;
    private Boolean isChecked;
    private Boolean isPwnull;
    private Boolean isSame;
    private Boolean isTyped;

    private final int SUCCESS = 200;
    public enum DuplicateCode {
        YES(100), NO(150);
        private final int value;
        DuplicateCode(int value){
            this.value = value;
        }
        public int getValue(){return value;}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.view_initialize();
        this.setListener();

    }//onCreate finished

    public void view_initialize() {

        mRgLine1 = findViewById(R.id.rg_1);
        mRgLine1.clearCheck();
        mRgLine1.setOnCheckedChangeListener(this);


        mRgLine2 = findViewById(R.id.rg_2);
        mRgLine1.clearCheck();
        mRgLine2.setOnCheckedChangeListener(this);

        mRb = new RadioButton[8];
        for (int i = 0; i < mRadioId.length; i++) {
            mRb[i] = findViewById(mRadioId[i]);
        }

        mEdtId = findViewById(R.id.edt_sign_up_id);
        mEdtSignUpPassword = findViewById(R.id.edt_sign_up_password);
        mEdtSignUpPasswordOk = findViewById(R.id.edt_sign_up_password_ok);
        mEdtBusinessNumber = findViewById(R.id.edt_business_number);
        mEdtPost = findViewById(R.id.edt_post);
        mEdtPostDetail = findViewById(R.id.edt_post_detail);

        mBtnDuplicate = findViewById(R.id.btn_duplicate);
        mBtnVerify = findViewById(R.id.btn_verify);
        //mBtnPost = findViewById(R.id.btn_post);
        //mTvSignUp = findViewById(R.id.tv_sign_up);

        mCatering = -1;
        isChecked = false;
        isPwnull = false;
        isSame = false;
        isTyped = false;

    }//view_initialize finished

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_duplicate:
                if (mEdtId.getText().toString().equals("")) {
                    showCustomToast(getString(R.string.EdtId));
                } else duplicateCheck();
                break;
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.btn_verify:
                mBtnVerify.setBackgroundResource(R.drawable.btn_duplicate_ok);
                mBtnVerify.setText(getResources().getString(R.string.btn_verify_ok));
                mBtnVerify.setTextColor(getResources().getColor(R.color.white));
                mBtnVerify.setClickable(false);
                mEdtBusinessNumber.setBackground(null);
                mEdtPostDetail.requestFocus();
                break;
            case R.id.btn_post:
                break;
            case R.id.tv_sign_up:
                if (mRgLine1.getCheckedRadioButtonId() != -1) {
                    mCatering = mRgLine1.indexOfChild(mRgLine1.findViewById(mRgLine1.getCheckedRadioButtonId())) + 1;
                    Log.d(TAG, "Line1:" + mCatering);
                } else if (mRgLine2.getCheckedRadioButtonId() != -1) {
                    mCatering = mRgLine2.indexOfChild(mRgLine2.findViewById(mRgLine2.getCheckedRadioButtonId())) + 6;
                    Log.d(TAG, "Line2:" + mCatering);
                }

                //패스워드가 null인지 체크
                if (mEdtSignUpPassword.getText().toString().equals("")) {
                    isPwnull = true;
                } else isPwnull = false;

                //비밀번호 확인 체크
                if (mEdtSignUpPassword.getText().toString().equals(mEdtSignUpPasswordOk.getText().toString())) {
                    isSame = true;
                } else isSame = false;

                //요식업 체크
                if (mCatering == -1) {
                    isTyped = false;
                } else isTyped = true;

                if (isChecked) {
                    if (!isPwnull) {
                        if (isSame) {
                            if (isTyped) {
                                signUp(mCatering);
                            } else showCustomToast2(getString(R.string.CheckCatering));
                        } else showCustomToast2(getString(R.string.NotMatchPassword));
                    } else showCustomToast2(getString(R.string.FillPassword));
                } else  showCustomToast2(getString(R.string.CheckDuplicate));
                break;
        }
    }//onClick finished

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if (radioGroup == mRgLine1) {
            if (checkedId != -1) {
                mRgLine2.setOnCheckedChangeListener(null);
                mRgLine2.clearCheck();
                mRgLine2.setOnCheckedChangeListener(this);
            }
        } else if (radioGroup == mRgLine2) {
            if (checkedId != -1) {
                mRgLine1.setOnCheckedChangeListener(null);
                mRgLine1.clearCheck();
                mRgLine1.setOnCheckedChangeListener(this);
            }
        }
    }

    //중복확인 함수
    private void duplicateCheck() {
        showProgressDialog();
        final SignUpService mainService = new SignUpService(this, mEdtId.getText().toString());
        mainService.getDuplicate();
    }

    //회원가입 함수
    private void signUp(int business) {

        JSONObject params = new JSONObject();
        try {
            params.put("id", mEdtId.getText().toString());
            params.put("pw", mEdtSignUpPasswordOk.getText().toString());
            params.put("type", business);
            params.put("address", mEdtPost.getText().toString() + " " + mEdtPostDetail.getText().toString());
        } catch (Exception e) {
         //   Log.d(TAG, "error: " + e);
            return;
        }
        showProgressDialog();
        final SignUpService signUpService = new SignUpService(this, params);
       // Log.d(TAG, params.getJ);
        signUpService.postSignUp();
    }

    //중복확인 성공, 실패
    @Override
    public void DuplicateSuccess(int code, String text) {
        hideProgressDialog();
        if (code == DuplicateCode.NO.value) {
            mBtnDuplicate.setBackgroundResource(R.drawable.btn_duplicate_ok);
            mBtnDuplicate.setText(getResources().getString(R.string.btn_duplicate_ok));
            mBtnDuplicate.setTextColor(getResources().getColor(R.color.white));
            mBtnDuplicate.setClickable(false);
            mEdtId.clearFocus();
            mEdtId.setBackground(null);
            //비밀번호 입력란으로 focus 이동
            mEdtSignUpPassword.requestFocus();
            showCustomToast2(text);
            isChecked = true;
        } else if (code == DuplicateCode.YES.value) {
            showCustomToast2(text);
            isChecked = false;
        }
    }

    @Override
    public void DuplicateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    //회원가입 성공, 실패
    @Override
    public void SignUpSuccess(int code, String text) {

        if (code != SUCCESS) {
            hideProgressDialog();
            showCustomToast2(text);
        } else {
            hideProgressDialog();
            showCustomToast2(text);
            finish();
        }
    }

    @Override
    public void SignUpFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void setListener() {

        mEdtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mBtnDuplicate.setBackgroundResource(R.drawable.btn_duplicate);
                mBtnDuplicate.setText(getResources().getString(R.string.btn_duplicate));
                mBtnDuplicate.setTextColor(getResources().getColor(R.color.normal));
                mBtnDuplicate.setClickable(true);
                isChecked = false;
            }
        });

        mEdtSignUpPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mEdtSignUpPassword.setBackground(null);
                    mEdtSignUpPasswordOk.requestFocus();
                }
                return false;
            }
        });

        mEdtSignUpPasswordOk.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mEdtSignUpPasswordOk.setBackground(null);
                    mEdtBusinessNumber.requestFocus();
                }
                return false;
            }
        });

        mEdtPostDetail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mEdtPostDetail.setBackground(null);
                    mEdtPostDetail.clearFocus();
                }
                return false;
            }
        });
    }

}//SignUpActivity finished
