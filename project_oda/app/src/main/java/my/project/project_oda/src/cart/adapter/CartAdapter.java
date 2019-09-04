package my.project.project_oda.src.cart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.cart.models.CartItem;
import my.project.project_oda.src.product.models.ReviewItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<CartItem> mCartList;
    int mNum;
    String mCount;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_price;
        ImageView iv_image;
        ImageView iv_close;
        TextView tv_minus;
        TextView tv_plus;
        TextView tv_num;
        CheckBox chbox;

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)

            tv_title = itemView.findViewById(R.id.tv_item_cart_title);
            tv_price = itemView.findViewById(R.id.tv_item_cart_price);
            iv_image = itemView.findViewById(R.id.iv_item_cart_image);
            iv_close = itemView.findViewById(R.id.iv_item_cart_close);
            tv_minus = itemView.findViewById(R.id.tv_item_cart_minus);
            tv_plus = itemView.findViewById(R.id.tv_item_cart_plus);
            tv_num = itemView.findViewById(R.id.tv_item_cart_num);
            chbox = itemView.findViewById(R.id.chbox_cart_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(view, pos);
                        }
                    }
                }
            });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public CartAdapter(Context mContext, ArrayList<CartItem> mCartList) {
        this.mContext = mContext;
        this.mCartList = mCartList;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_cart_list, parent, false);
        CartAdapter.ViewHolder vh = new CartAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {
        CartItem cartItem = mCartList.get(position);
        holder.tv_title.setText(cartItem.getpName());
        holder.tv_price.setText(cartItem.getPrice());
        Glide.with(mContext).load(cartItem.getImage()).placeholder(R.drawable.ic_logo).into(holder.iv_image);
        holder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCartList.remove(position);
                notifyDataSetChanged();
            }
        });

        mNum = Integer.parseInt(holder.tv_num.getText().toString());
        holder.tv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount = String.valueOf(mNum + 1);
                mNum += 1;
                holder.tv_num.setText(mCount);
            }
        });

        holder.tv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNum >0 ) {
                    mCount = String.valueOf(mNum - 1);
                    mNum -= 1;
                    holder.tv_num.setText(mCount);
                }else{}
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mCartList.size();
    }
}
