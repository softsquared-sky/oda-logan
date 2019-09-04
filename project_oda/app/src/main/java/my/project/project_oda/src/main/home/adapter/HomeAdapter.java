package my.project.project_oda.src.main.home.adapter;

import android.content.Context;
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
import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.main.home.models.Home_Item;

import static my.project.project_oda.src.ApplicationClass.myFormatter;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<Home_Item> mHomeList;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }
/*
    public interface OnMyItemCheckedChanged {
        public void onItemCheckedChanged(Myitem item, boolean isChcked);
    }

    private OnMyItemCheckedChanged mOnMyItemCheckedChanged;

    public void setmOnMyItemCheckedChanged(OnMyItemCheckedChanged onMyItemCheckedChanged) {
        this.mOnMyItemCheckedChanged = onMyItemCheckedChanged;
    }
*/
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
    public HomeAdapter(Context mContext, ArrayList<Home_Item> mHomeList) {
        this.mContext = mContext;
        this.mHomeList = mHomeList;
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
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, final int position) {
        Home_Item homeItem = mHomeList.get(position);
        holder.tv_title.setText(homeItem.getTitle());
        holder.tv_oda_price.setText(myFormatter.format(homeItem.getPrice()) + "원");
        holder.chbox_home_item.setChecked(homeItem.isChecked());
        Glide.with(mContext).load(homeItem.getImageUrl()).into(holder.iv_image);

        holder.chbox_home_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox chbox = (CheckBox) view;
                mHomeList.get(position).setChecked(chbox.isChecked());
            }
        });
/*
        holder.chbox_home_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(mOnMyItemCheckedChanged != null){
                        mOnMyItemCheckedChanged.onItemCheckedChanged(item, isChecked);
                    }
                }
            }
        });
*/
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mHomeList.size();
    }
}
