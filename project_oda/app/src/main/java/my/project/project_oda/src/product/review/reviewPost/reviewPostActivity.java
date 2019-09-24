package my.project.project_oda.src.product.review.reviewPost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import my.project.project_oda.R;
import my.project.project_oda.src.BaseActivity;
import my.project.project_oda.src.product.review.reviewPost.interfaces.ReviewPostActivityView;

public class reviewPostActivity extends BaseActivity implements ReviewPostActivityView {

    private EditText mEdtReviewPostTitle;
    private EditText mEdtReviewPostContent;
    private ImageView mIvReviewPost;
    private ImageView mIvPostReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_post);
        this.initialize();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_review_post_back:
                finish();
                break;
            case R.id.iv_review_post:
                postProductReview();
                break;
        }
    }

    public void initialize() {

        mEdtReviewPostTitle = findViewById(R.id.edt_review_post_title);
        mEdtReviewPostContent = findViewById(R.id.edt_review_post_content);
        mIvPostReview = findViewById(R.id.iv_review_post);
        mIvReviewPost = findViewById(R.id.iv_review_post_image);

        Glide.with(this).load(getIntent().getStringExtra("url")).placeholder(R.drawable.ic_logo).into(mIvReviewPost);

        mIvPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postProductReview();
            }
        });

    }

    void postProductReview() {

        JSONObject params;
        try {
            params = new JSONObject();
            params.put("pNum", getIntent().getIntExtra("productNumber", 0));
            params.put("reviewTitle", mEdtReviewPostTitle.getText().toString());
            params.put("review", mEdtReviewPostContent.getText().toString());
            params.put("reviewImage", getIntent().getStringExtra("url"));
        } catch (Exception e) {
            Log.d("dd", "error: " + e);
            return;
        }

        showProgressDialog();
        final ReviewPostService reviewService = new ReviewPostService(this, params);
        reviewService.postProductReview();
    }

    @Override
    public void postReviewSuccess(String message) {
        hideProgressDialog();
        showCustomToast(message);
        finish();
    }

    @Override
    public void postReviewFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
        finish();
    }
}

