package my.project.project_oda.src.main.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.main.home.adapter.HomeAdapter;
import my.project.project_oda.src.main.home.interfaces.HomeActivityView;
import my.project.project_oda.src.main.home.models.Home_Item;
import my.project.project_oda.src.product.ProductActivity;

public class Fragment_home extends Fragment implements HomeActivityView {

    ArrayList<Home_Item> mHomeItemList;
    private RecyclerView mRvHome;
    private CheckBox mChboxSelectAll;
    private TextView mTvSelectAll;
    private TextView mTvItemSelected;
    HomeAdapter mHomeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeItemList = new ArrayList<>();
        mRvHome = view.findViewById(R.id.rv_home);
        mChboxSelectAll = view.findViewById(R.id.chbox_home_select_all);
        mTvSelectAll = view.findViewById(R.id.tv_select_all);
        mTvItemSelected = view.findViewById(R.id.tv_num_item_selected);
        mHomeAdapter = new HomeAdapter(getActivity(), mHomeItemList);

        mRvHome.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvHome.setAdapter(mHomeAdapter);

        String testUrl = "http://image1.coupangcdn.com/image/vendor_inventory/73f1/4b90e1a2828cc9cb087663ab3a6ad90728066597161499f035ba4d8d0b57.jpg";

        //테스트 더미 데이터
        mHomeItemList.add(new Home_Item(testUrl, "계란", 5000, false));
        mHomeItemList.add(new Home_Item(testUrl, "무정란", 7500, false));
        mHomeItemList.add(new Home_Item(testUrl, "유정란", 10000, false));
        mHomeItemList.add(new Home_Item(testUrl, "토종란", 15000, false));
        mHomeAdapter.notifyDataSetChanged();

        this.setListener();

        return view;
    }

    public void setListener(){

        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra("title", mHomeItemList.get(pos).getTitle());
                intent.putExtra("price",mHomeItemList.get(pos).getPrice());
                intent.putExtra("url",mHomeItemList.get(pos).getImageUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getProductSuccess() {

    }

    @Override
    public void getProductFailure() {

    }
}
