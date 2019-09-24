package my.project.project_oda.src.product.review;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static my.project.project_oda.src.ApplicationClass.TAG;

import java.util.ArrayList;
import java.util.List;

import my.project.project_oda.R;
import my.project.project_oda.src.product.ProductActivity;
import my.project.project_oda.src.product.review.adapter.ReviewAdapter;
import my.project.project_oda.src.product.review.models.ReviewItem;
import my.project.project_oda.src.product.review.models.ReviewResult;
import my.project.project_oda.src.product.review.interfaces.ReviewFragmentView;

public class FragmentProductReview extends Fragment implements ReviewFragmentView {

    private Context mContext;
    private ArrayList<ReviewItem> mReviewItemList;
    private RecyclerView mRvReview;
    private ReviewAdapter mReviewAdapter;
    private int mProductNumber;

    public FragmentProductReview(Context context, int mProductNumber) {
        mContext = context;
        this.mProductNumber = mProductNumber;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_review, container, false);

        mReviewItemList = new ArrayList<>();
        mRvReview = view.findViewById(R.id.rv_review);
        mReviewAdapter = new ReviewAdapter(getActivity(), mReviewItemList);
        mRvReview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvReview.setAdapter(mReviewAdapter);

        getProductReview();

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    public void getProductReview() {
        ((ProductActivity) getActivity()).showProgressDialog();
        final ReviewService reviewService = new ReviewService(this, mProductNumber);
        reviewService.getProductReview();
    }

    @Override
    public void getReviewSuccess(List<ReviewResult> response) {

        if (response != null) {
            for (ReviewResult result : response) {
                mReviewItemList.add(new ReviewItem(mProductNumber, result.getReview(), result.getReview(), result.getReviewImage(),
                        result.getId(), result.getReviewDate().replace("-", "/").substring(2)));
            }
            mReviewAdapter.notifyDataSetChanged();
        }
        ((ProductActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void getReviewFailure(String message) {
        ((ProductActivity) getActivity()).hideProgressDialog();
        ((ProductActivity) getActivity()).showCustomToast(message);
    }

}
