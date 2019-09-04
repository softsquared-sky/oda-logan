package my.project.project_oda.src.product.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import my.project.project_oda.R;

import static my.project.project_oda.src.ApplicationClass.TAG;

public class fragmentProductDetail extends Fragment {

    private Context mContext;
    private TextView mTvProductPerWrap;
    private TextView mTvProductGrade;
    private TextView mTvProductResume;
    private TextView mTvProductHandling;
    private TextView mTvProductOrigin;
    private TextView mTvProductClean;
    private ImageView mIvProductImage1;
    private ImageView mIvProductImage2;
    private LinearLayout mLinearProductImage;

    public fragmentProductDetail(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        mTvProductPerWrap = view.findViewById(R.id.tv_product_per_wrap);
        mTvProductGrade = view.findViewById(R.id.tv_product_grade);
        mTvProductResume = view.findViewById(R.id.tv_product_resume);
        mTvProductHandling = view.findViewById(R.id.tv_product_handling);
        mTvProductOrigin = view.findViewById(R.id.tv_product_origin);
        mTvProductClean = view.findViewById(R.id.tv_product_clean);
        mLinearProductImage = view.findViewById(R.id.linear_product);
        mIvProductImage1 = view.findViewById(R.id.iv_product_image_1);
        mIvProductImage2 = view.findViewById(R.id.iv_product_image_2);

        mTvProductPerWrap.setText(getArguments().getString("qpp"));
        mTvProductGrade.setText(getString(R.string.product_grade_tools));
        mTvProductResume.setText(getString(R.string.product_resume_tools));
        mTvProductHandling.setText(getArguments().getString("storeMethod"));
        mTvProductOrigin.setText(getArguments().getString("origin"));
        mTvProductClean.setText(getString(R.string.product_clean_tools));
        Glide.with(mContext).load(getArguments().getString("image1")).into(mIvProductImage1);
        Glide.with(mContext).load(getArguments().getString("image2")).into(mIvProductImage2);
/*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < getArguments().getInt("size"); i++) {
            ImageView ivProductDetail = new ImageView(mContext);
            Glide.with(mContext).load(getArguments().getString(i+"image")).into(ivProductDetail);
            ivProductDetail.setLayoutParams(layoutParams);
            ivProductDetail.setScaleType(ImageView.ScaleType.FIT_XY);
            mLinearProductImage.addView(ivProductDetail);
        }
*/
        return view;
    }
}
