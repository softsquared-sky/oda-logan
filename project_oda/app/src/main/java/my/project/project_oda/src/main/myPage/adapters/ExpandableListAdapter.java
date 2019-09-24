package my.project.project_oda.src.main.myPage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import my.project.project_oda.R;
import my.project.project_oda.src.main.myPage.fragments.FragmentMyPage;
import my.project.project_oda.src.main.myPage.models.Children;
import my.project.project_oda.src.main.myPage.models.Parent;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Parent> mParent;
    private Context mContext;
    private FragmentMyPage mFragmentMyPage;
    HashMap<Parent, ArrayList<Children>> mChildren;

    public ExpandableListAdapter(Context mContext, ArrayList<Parent> mParent, HashMap<Parent, ArrayList<Children>> mChildren, FragmentMyPage mFragmentMyPage) {
        this.mContext = mContext;
        this.mParent = mParent;
        this.mChildren = mChildren;
        this.mFragmentMyPage = mFragmentMyPage;
        //mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mChildren.get(mParent.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return mParent.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mChildren.get(mParent.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int position, final boolean isExpanded, View view, ViewGroup viewGroup) {
        final Parent parent = (Parent) getGroup(position);
        if (view == null) {
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.item_my_page_parent, null);
        }
        TextView parentSerial = view.findViewById(R.id.tv_item_my_page_serial);
        ImageView parentImage = view.findViewById(R.id.iv_item_my_page_parent_image);
        TextView parentTitle = view.findViewById(R.id.tv_item_my_page_parent_title);
        TextView parentPrice = view.findViewById(R.id.tv_item_my_page_parent_price);
        TextView parentCount = view.findViewById(R.id.tv_item_my_page_parent_num);
        TextView parentDelivery = view.findViewById(R.id.tv_item_my_page_parent_delivery);

        parentSerial.setText(parent.getmSerial());
        Glide.with(mContext).load(parent.getmImage()).placeholder(R.drawable.ic_app_icon).into(parentImage);
        parentTitle.setText(parent.getmTitle());
        parentPrice.setText(parent.getmPrice());
        parentCount.setText(parent.getmCount());
        parentDelivery.setText(parent.getmDelivery());

        final TextView parentExpandFold = view.findViewById(R.id.tv_my_page_parent_expand_fold);

        parentExpandFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExpanded) {
                    parentExpandFold.setText(mContext.getResources().getString(R.string.my_page_list_parent_fold));
                    mFragmentMyPage.expandList(position);
                } else {
                    parentExpandFold.setText(mContext.getResources().getString(R.string.my_page_list_parent_expand));
                    mFragmentMyPage.foldList(position);
                }
            }
        });

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup viewGroup) {
        Children child = (Children) getChild(groupPosition, childPosition);
        //if (childPosition != 0) {
            if (convertView == null) {
                mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.item_my_page_child, null);
            }

            ImageView childImage = convertView.findViewById(R.id.iv_item_my_page_child_image);
            TextView childTitle = convertView.findViewById(R.id.tv_item_my_page_child_title);
            TextView childPrice = convertView.findViewById(R.id.tv_item_my_page_child_price);
            TextView childCount = convertView.findViewById(R.id.tv_item_my_page_child_num);
            TextView childDelivery = convertView.findViewById(R.id.tv_item_my_page_child_delivery);

            //Children child = mParent.get(groupPosition).getmArrayChildren().get(childPosition);
            Glide.with(mContext).load(child.getmImage()).placeholder(R.drawable.ic_app_icon).into(childImage);
            childTitle.setText(child.getmTitle());
            childPrice.setText(child.getmPrice());
            childCount.setText(child.getmCount());
            childDelivery.setText(child.getmDelivery());

        //}
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}