package my.project.project_oda.src.product.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import static my.project.project_oda.src.ApplicationClass.*;

import my.project.project_oda.R;
import my.project.project_oda.src.product.adapters.ReviewAdapter;
import my.project.project_oda.src.product.models.ReviewItem;

public class fragmentProductReview extends Fragment {

    private Context mContext;
    private ArrayList<ReviewItem> mReviewItemList;
    private RecyclerView mRvReview;
    private ReviewAdapter mReviewAdapter;

    public fragmentProductReview(Context context){
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_review, container, false);

        mReviewItemList = new ArrayList<>();
        mRvReview = view.findViewById(R.id.rv_review);
        mReviewAdapter = new ReviewAdapter(getActivity(), mReviewItemList);
        mRvReview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvReview.setAdapter(mReviewAdapter);

        mReviewItemList.add(new ReviewItem(1, "좋아요ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ","항상 감사합니다","","smj7162", curTime()));
        mReviewItemList.add(new ReviewItem(1, "좋아요","항상 감사합니다","","smj7162", curTime()));
        mReviewItemList.add(new ReviewItem(1, "좋아요","항상 감사합니다","","smj7162", curTime()));
        mReviewItemList.add(new ReviewItem(1, "좋아요ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ","항상 감사합니다 항상 감사합니다 항상 감사합니다 항상 감사합니다 항상 감사합니다","","smj7162", curTime()));
        mReviewItemList.add(new ReviewItem(1, "좋아요","항상 감사합니다","","smj7162", curTime()));
        mReviewItemList.add(new ReviewItem(1, "좋아요ㅎㅎㅎㅎㅎㅎ","항상 감사합니다","","smj7162", curTime()));
        mReviewItemList.add(new ReviewItem(1, "좋아요ㅎㅎㅎ","항상 감사합니다","","smj7162", curTime()));
        mReviewAdapter.notifyDataSetChanged();

        return  view;
    }

}
