package my.project.project_oda.src.product.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.product.review.models.ReviewItem;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<ReviewItem> mReviewList;

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_content;
        TextView tv_title;
        TextView tv_writer;
        ImageView iv_image;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)

            tv_title = itemView.findViewById(R.id.tv_item_review_title);
            tv_content = itemView.findViewById(R.id.tv_item_review_content);
            iv_image = itemView.findViewById(R.id.iv_item_review_image);
            tv_writer = itemView.findViewById(R.id.tv_item_review_writer);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(view, pos);
                        }
                    }
                }
            });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public ReviewAdapter(Context mContext, ArrayList<ReviewItem> mReviewList) {
        this.mContext = mContext;
        this.mReviewList = mReviewList ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_review_list, parent, false) ;
        ReviewAdapter.ViewHolder vh = new ReviewAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, final int position) {
        ReviewItem reviewItem = mReviewList.get(position) ;
        holder.tv_title.setText(reviewItem.getReviewTitle());
        holder.tv_content.setText(reviewItem.getReview());
        //익명성 보장 위한 아이디 교체
        StringBuilder star = new StringBuilder("");
        for(int i=0; i<reviewItem.getReviewId().length()/2+1; i++){
            star.append("*");
        }
        String newId = reviewItem.getReviewId().substring(0, reviewItem.getReviewId().length()/2) + star;
        holder.tv_writer.setText(reviewItem.getReviewDate()+" "+newId+"님");
        Glide.with(mContext).load(reviewItem.getReviewImage()).placeholder(R.drawable.ic_app_icon).into(holder.iv_image);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mReviewList.size() ;
    }
}
