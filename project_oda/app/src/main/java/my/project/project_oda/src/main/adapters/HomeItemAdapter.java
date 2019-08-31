package my.project.project_oda.src.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import java.util.ArrayList;
import my.project.project_oda.R;
import my.project.project_oda.src.main.home.models.Home_Item;

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


        return v;
    }

    public class ViewHolder {

        public ViewHolder(View convertView) {
        }
    }
}
