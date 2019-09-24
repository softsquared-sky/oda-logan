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

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.cart.CartActivity;
import my.project.project_oda.src.cart.models.CartItem;

import static my.project.project_oda.src.ApplicationClass.*;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<CartItem> mCartList;
    private CartActivity mCartActivity;
    private boolean mIsCart;

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
    public CartAdapter(Context mContext, ArrayList<CartItem> mCartList, CartActivity cartActivity, boolean mIsCart) {
        this.mContext = mContext;
        this.mCartList = mCartList;
        this.mCartActivity = cartActivity;
        this.mIsCart = mIsCart;
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
        final CartItem cartItem = mCartList.get(position);
        holder.tv_title.setText(cartItem.getpName());
        holder.tv_price.setText(myFormatter.format(cartItem.getPrice()).concat("원"));
        Glide.with(mContext).load(cartItem.getImage()).placeholder(R.drawable.ic_logo).into(holder.iv_image);
        holder.chbox.setChecked(cartItem.isChecked());

        holder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //계산
                mCartActivity.setTotalPriceMinus(cartItem.getPrice() * cartItem.getCount());

                if (cartItem.isChecked()) {
                    mCartActivity.setSelectedPriceMinus(cartItem.getPrice() * cartItem.getCount());
                }
                //제거
                mCartList.remove(position);
                notifyDataSetChanged();
                if (mCartList.size() == 0) {
                    if (mIsCart) {
                        mCartActivity.hideBasket();
                    } else {
                        mCartActivity.hideDirectOrder();
                    }
                }
            }
        });

        holder.tv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.setCount(cartItem.getCount() + 1);
                holder.tv_num.setText(String.valueOf(cartItem.getCount()));
                mCartActivity.setTotalPricePlus(cartItem.getPrice());
                if (cartItem.isChecked()) {
                    mCartActivity.setSelectedPricePlus(cartItem.getPrice());
                }
            }
        });

        holder.tv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItem.getCount() > 0) {
                    cartItem.setCount(cartItem.getCount() - 1);
                    holder.tv_num.setText(String.valueOf(cartItem.getCount()));
                    mCartActivity.setTotalPriceMinus(cartItem.getPrice());
                    if (cartItem.isChecked()) {
                        mCartActivity.setSelectedPriceMinus(cartItem.getPrice());
                    }
                }
            }
        });

        holder.chbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.chbox.isChecked()) {
                    mCartActivity.setSelectedPricePlus(cartItem.getPrice() * cartItem.getCount());
                    mCartList.get(position).setChecked(true);
                    notifyDataSetChanged();
                } else {
                    mCartActivity.setSelectedPriceMinus(cartItem.getPrice() * cartItem.getCount());
                    mCartList.get(position).setChecked(false);
                    notifyDataSetChanged();
                }
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mCartList.size();
    }
}
