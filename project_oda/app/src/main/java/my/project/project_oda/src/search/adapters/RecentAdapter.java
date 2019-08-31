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
import my.project.project_oda.src.search.models.Recent_Item;

public class RecentAdapter extends BaseAdapter implements Filterable{

    private Context mContext;
    private ArrayList<Recent_Item> listitem;
    private ArrayList<Recent_Item> filteredItem;
    Filter listFilter;
    private ViewHolder mViewHolder;
    LayoutInflater mLayoutInflater;

    public RecentAdapter(Context mContext, ArrayList<Recent_Item> listitem) {
        this.listitem = listitem;
        this.mContext = mContext;
        this.filteredItem = listitem;
        mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return filteredItem.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
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
        mViewHolder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filteredItem.remove(position);
                notifyDataSetChanged();
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

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = listitem;
                results.count = listitem.size();
            } else {

                ArrayList<Recent_Item> itemList = new ArrayList<>();

                for (Recent_Item item : listitem) {
                    if (item.getKeyword().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item);
                        Log.d("로그",item.getKeyword()+"filtered");
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredItem = (ArrayList<Recent_Item>)results.values;

            if(results.count > 0){
                notifyDataSetChanged();
            }else notifyDataSetInvalidated();

        }
    }
}
