package my.project.project_oda.src.main.home.fragments;

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
import java.util.List;

import my.project.project_oda.R;
import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.main.home.HomeService;
import my.project.project_oda.src.main.home.adapter.HomeAdapter;
import my.project.project_oda.src.main.home.interfaces.HomeActivityView;
import my.project.project_oda.src.main.home.models.Home_Item;
import my.project.project_oda.src.main.home.models.Result;
import my.project.project_oda.src.product.ProductActivity;

public class Fragment_home extends Fragment implements HomeActivityView {

    ArrayList<Home_Item> mHomeItemList;
    /*
    private RecyclerView mRvHome;
    private CheckBox chboxSelectAll;
    private TextView tvSelectAll;
    private TextView tvItemSelected;
    */
    HomeAdapter mHomeAdapter;
    int mLastTurn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView mRvHome;
        CheckBox chboxSelectAll;
        TextView tvSelectAll;
        TextView tvItemSelected;

        mHomeItemList = new ArrayList<>();
        mRvHome = view.findViewById(R.id.rv_home);
        chboxSelectAll = view.findViewById(R.id.chbox_home_select_all);
        tvSelectAll = view.findViewById(R.id.tv_select_all);
        tvItemSelected = view.findViewById(R.id.tv_num_item_selected);
        mHomeAdapter = new HomeAdapter(getActivity(), mHomeItemList);

        mRvHome.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvHome.setAdapter(mHomeAdapter);

        mLastTurn = 0;

        String testUrl = "http://image1.coupangcdn.com/image/vendor_inventory/73f1/4b90e1a2828cc9cb087663ab3a6ad90728066597161499f035ba4d8d0b57.jpg";
        String testUrl2 = "http://thumbnail14.coupangcdn.com/thumbnails/remote/48x48ex/image/retail/images/2019/02/28/14/2/32f59119-559b-430e-91aa-bc6ce8a6af9e.jpg";
        //테스트 더미 데이터
        mHomeItemList.add(new Home_Item(testUrl, "계란", 5000, false, 1));
        mHomeItemList.add(new Home_Item(testUrl, "무정란", 7500, false, 1));
        mHomeItemList.add(new Home_Item(testUrl, "유정란", 10000, false, 1));
        mHomeItemList.add(new Home_Item(testUrl, "토종란", 15000, false, 1));
        mHomeItemList.add(new Home_Item(testUrl2, "친환경 양배추", 2640, false, 7));
        mHomeItemList.add(new Home_Item(testUrl2, "양배추", 1640, false, 7));
        mHomeItemList.add(new Home_Item(testUrl2, "미국 양배추", 2000, false, 7));
        mHomeItemList.add(new Home_Item(testUrl2, "중국 양배추", 640, false, 7));
        mHomeAdapter.notifyDataSetChanged();

        this.setListener();

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chbox_home_select_all:

                break;
        }
    }

    public void setListener() {

        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra("title", mHomeItemList.get(pos).getTitle());
                intent.putExtra("price", mHomeItemList.get(pos).getPrice());
                intent.putExtra("url", mHomeItemList.get(pos).getImageUrl());
                startActivity(intent);
            }
        });
    }

    void getProduct() {
        //fragment에서 ProgressDialog 보이기
        ((MainActivity) getActivity()).showProgressDialog();
        final HomeService homeService = new HomeService(this, "계란", mLastTurn);
        homeService.getProducts();
    }

    @Override
    public void getProductSuccess(List<Result> resultList) {
        ((MainActivity) getActivity()).hideProgressDialog();

    }

    @Override
    public void getProductFailure(String message) {
        ((MainActivity) getActivity()).hideProgressDialog();
        ((MainActivity) getActivity()).showCustomToast(message);
    }
}
