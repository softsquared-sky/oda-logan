package my.project.project_oda.src.main.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.main.home.fragments.FragmentHome;
import my.project.project_oda.src.main.home.models.Home_Item;

import static my.project.project_oda.src.ApplicationClass.*;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<Home_Item> mHomeList;
    private FragmentHome mFragmentHome;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_oda_price;
        TextView tv_title;
        ImageView iv_image;
        CheckBox chbox_home_item;

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tv_oda_price = itemView.findViewById(R.id.tv_home_price);
            tv_title = itemView.findViewById(R.id.tv_home_title);
            iv_image = itemView.findViewById(R.id.iv_home_item);
            chbox_home_item = itemView.findViewById(R.id.chbox_item);

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
    public HomeAdapter(Context mContext, ArrayList<Home_Item> mHomeList, FragmentHome fragmentHome) {
        this.mContext = mContext;
        this.mHomeList = mHomeList;
        this.mFragmentHome = fragmentHome;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_home_list, parent, false);
        HomeAdapter.ViewHolder vh = new HomeAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final HomeAdapter.ViewHolder holder, final int position) {
        final Home_Item homeItem = mHomeList.get(position);
        holder.tv_title.setText(homeItem.getTitle());
        holder.tv_oda_price.setText(myFormatter.format(homeItem.getPrice()).concat("원"));
        holder.chbox_home_item.setChecked(homeItem.isChecked());
        Glide.with(mContext).load(homeItem.getImageUrl()).override(200, 200).into(holder.iv_image);

        holder.chbox_home_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox chbox = (CheckBox) view;
                mHomeList.get(position).setChecked(chbox.isChecked());
            }
        });

        holder.chbox_home_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.chbox_home_item.isChecked()) {
                    mFragmentHome.setSelectItemNumPlus();
                } else {
                    mFragmentHome.setSelectItemNumMinus();
                }
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mHomeList.size();
    }
}
