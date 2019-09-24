package my.project.project_oda.src.search.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.search.models.PopularItem;
import my.project.project_oda.src.search.models.RecentItem;

public class PopularAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<PopularItem> mListItem;
    private ViewHolder mViewHolder;
    LayoutInflater mLayoutInflater;

    public PopularAdapter(Context mContext, ArrayList<PopularItem> mListItem) {
        this.mListItem = mListItem;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mListItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;

        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.item_popular_list, null);
            mViewHolder = new ViewHolder(v);
            v.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) v.getTag();
        }

        PopularItem item = (PopularItem) getItem(position);
        mViewHolder.keyword.setText(item.getKeyword());
        mViewHolder.order.setText(String.valueOf(position + 1));

        return v;
    }

    public class ViewHolder {
        private TextView keyword;
        private TextView order;

        public ViewHolder(View convertView) {
            keyword = convertView.findViewById(R.id.item_search_popular_keyword);
            order = convertView.findViewById(R.id.item_search_popular_order);
        }
    }
}
