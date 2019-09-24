package my.project.project_oda.src.order.adapter;

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
import my.project.project_oda.src.order.models.OrderItem;
import static my.project.project_oda.src.ApplicationClass.myFormatter;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<OrderItem> mOrderList;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvPrice;
        TextView tvNumOfProduct;
        ImageView ivImage;

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)

            tvTitle = itemView.findViewById(R.id.tv_item_order_title);
            tvPrice = itemView.findViewById(R.id.tv_item_order_price);
            tvNumOfProduct = itemView.findViewById(R.id.tv_item_order_num);
            ivImage = itemView.findViewById(R.id.iv_item_order_image);

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public OrderAdapter(Context mContext, ArrayList<OrderItem> mOrderList) {
        this.mContext = mContext;
        this.mOrderList = mOrderList;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_order_list, parent, false);
        OrderAdapter.ViewHolder vh = new OrderAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, final int position) {
        OrderItem orderItem = mOrderList.get(position);
        holder.tvTitle.setText(orderItem.getOrderTitle());
        holder.tvPrice.setText(myFormatter.format(orderItem.getOrderPrice()).concat("원"));
        holder.tvNumOfProduct.setText("/".concat(String.valueOf(orderItem.getOrderNumOfProduct())).concat("개"));
        Glide.with(mContext).load(orderItem.getOrderImage()).placeholder(R.drawable.ic_app_icon).into(holder.ivImage);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mOrderList.size();
    }
}
