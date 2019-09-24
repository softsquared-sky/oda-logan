package my.project.project_oda.src.product.detail;

import android.content.Context;
import android.content.Intent;
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
import my.project.project_oda.src.product.ProductActivity;
import my.project.project_oda.src.product.detail.interfaces.DetailFragmentView;
import my.project.project_oda.src.product.detail.models.Result;

import static my.project.project_oda.src.ApplicationClass.TAG;
import static my.project.project_oda.src.ApplicationClass.myFormatter;

public class fragmentProductDetail extends Fragment implements DetailFragmentView {

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
    private ImageView mIvProductDetailMainImage;
    private TextView mTvProductDetailTitle;
    private TextView mTvProductDetailPrice;
    private int mProductNumber;


    public fragmentProductDetail(Context context, int pNum) {
        this.mContext = context;
        this.mProductNumber = pNum;
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

        mIvProductDetailMainImage = view.findViewById(R.id.iv_detail_main);
        mTvProductDetailTitle = view.findViewById(R.id.tv_detail_title);
        mTvProductDetailPrice = view.findViewById(R.id.tv_detail_price);
        basicInformation();
        getProductBasic();

        return view;
    }

    void basicInformation() {

        Intent intent = getActivity().getIntent();
        //intent null 체크
        if (intent == null) {
            //인텐트를 제대로 못 받았으면 다시 돌아감, warning
            return;
        }

        //getExtras도 null check해야함
        if (intent.getExtras() == null) {
            return;
        }

        String title = intent.getExtras().getString("title");
        int price = intent.getExtras().getInt("price");
        String imageUrl = intent.getExtras().getString("url");

        Glide.with(mContext).load(imageUrl).placeholder(R.drawable.ic_logo).into(mIvProductDetailMainImage);
        mTvProductDetailTitle.setText(title);
        //concat생활화
        mTvProductDetailPrice.setText(myFormatter.format(price).concat("원"));
    }

    private void getProductBasic() {
        ((ProductActivity) getActivity()).showProgressDialog();
        final DetailService detailService = new DetailService(this, mProductNumber);
        detailService.getProductDetail();
    }

    @Override
    public void getProductDetailSuccess(Result result) {

        Glide.with(mContext).load(result.getImageResult().get(0).getImageUrl()).into(mIvProductImage1);
        Glide.with(mContext).load(result.getImageResult().get(1).getImageUrl()).into(mIvProductImage2);

        ((ProductActivity) getActivity()).hideProgressDialog();
        mTvProductPerWrap.setText(result.getQpp());
        mTvProductGrade.setText(getString(R.string.product_grade_tools));
        mTvProductResume.setText(getString(R.string.product_resume_tools));
        mTvProductHandling.setText(result.getStoreMethod());
        mTvProductOrigin.setText(result.getOrigin());
        mTvProductClean.setText(getString(R.string.product_clean_tools));
    }

    @Override
    public void getProductDetailFailure(String message) {
        ((ProductActivity) getActivity()).hideProgressDialog();
        ((ProductActivity) getActivity()).showCustomToast(message);
    }

}
