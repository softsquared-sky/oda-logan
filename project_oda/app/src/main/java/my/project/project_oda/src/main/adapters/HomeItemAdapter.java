package my.project.project_oda.src.main.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.main.models.Home_Item;
import my.project.project_oda.src.main.models.Recent_Item;

public class HomeItemAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Home_Item> listitem;
    Filter listFilter;
    private ViewHolder mViewHolder;
    LayoutInflater mLayoutInflater;

    public HomeItemAdapter(Context mContext, ArrayList<Home_Item> listitem) {
        this.listitem = listitem;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return listitem.size();
    }

    @Override
    public Object getItem(int i) {
        return listitem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;

        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.search_recent_item, null);
            mViewHolder = new ViewHolder(v);
            v.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) v.getTag();
        }

        Recent_Item item = (Recent_Item) getItem(position);
        mViewHolder.keyword.setText(item.getKeyword());

        v.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });



        return v;
    }

    public class ViewHolder {
        private TextView keyword;
        private ImageView iv_close;

        public ViewHolder(View convertView) {
            keyword = convertView.findViewById(R.id.tv_search_recent_list_keyword);
            iv_close = convertView.findViewById(R.id.iv_search_recent_list_close);
        }
    }
}
