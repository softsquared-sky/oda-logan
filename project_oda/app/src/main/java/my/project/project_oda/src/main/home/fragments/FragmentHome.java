package my.project.project_oda.src.main.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import my.project.project_oda.R;

import static my.project.project_oda.src.ApplicationClass.TAG;

import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.main.home.HomeService;
import my.project.project_oda.src.main.home.adapter.HomeAdapter;
import my.project.project_oda.src.main.home.interfaces.HomeActivityView;
import my.project.project_oda.src.main.home.models.Home_Item;
import my.project.project_oda.src.main.home.models.Result;
import my.project.project_oda.src.main.home.models.SpacesItemDecoration;
import my.project.project_oda.src.product.ProductActivity;

public class FragmentHome extends Fragment implements HomeActivityView {

    private ArrayList<Home_Item> mHomeItemList;
    private RecyclerView mRvHome;
    private CheckBox mChboxSelectAll;
    private TextView mTvSelectAll;
    private TextView mTvItemSelected;
    private TextView mTvNothing;
    private HomeAdapter mHomeAdapter;
    private int mLastTurn;
    private int mSelectCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeItemList = new ArrayList<>();
        mRvHome = view.findViewById(R.id.rv_home);
        mChboxSelectAll = view.findViewById(R.id.chbox_home_select_all);
        mTvSelectAll = view.findViewById(R.id.tv_select_all);
        mTvItemSelected = view.findViewById(R.id.tv_num_item_selected);
        mTvNothing = view.findViewById(R.id.tv_home_nothing);
        mHomeAdapter = new HomeAdapter(getActivity(), mHomeItemList, this);

        mRvHome.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvHome.setAdapter(mHomeAdapter);
        int spaceing = getResources().getDimensionPixelSize(R.dimen.home_spacing);
        mRvHome.addItemDecoration(new SpacesItemDecoration(spaceing));

        mLastTurn = 1;

        this.setListener();
        //초기 데이터 보여주기 위함
        this.getProduct(getActivity().getResources().getString(R.string.egg));
        mLastTurn += 1;
        this.getProduct(getActivity().getResources().getString(R.string.berry));
        mLastTurn += 1;
        this.getProduct(getActivity().getResources().getString(R.string.rice));
        mLastTurn += 1;
        this.getProduct(getActivity().getResources().getString(R.string.cabbage));

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chbox_home_select_all:
                mChboxSelectAll.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.checkbox));
                break;
            case R.id.tv_home_nothing:
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
                intent.putExtra("productNumber", mHomeItemList.get(pos).getpNum());
                startActivity(intent);
            }
        });

        mRvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount();

                if (lastVisibleItemPosition == itemTotalCount) {
                    mLastTurn += 1;
                    getProduct(getActivity().getResources().getString(R.string.egg));
                    mHomeAdapter.notifyDataSetChanged();
                }
            }
        });

        mChboxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    for (Home_Item item : mHomeItemList) {
                        if (!item.isChecked()) {
                            item.setChecked(true);
                            mSelectCount += 1;
                        }else{
                            continue;
                        }
                    }
                    setItemNum(mSelectCount);
                } else {
                    for (Home_Item item : mHomeItemList) {
                        if (item.isChecked()) {
                            item.setChecked(false);
                        }
                    }
                    mSelectCount = 0;
                    setItemNum(mSelectCount);
                }
                mHomeAdapter.notifyDataSetChanged();
            }
        });

    }

    private void setItemNum(int num) {
        mTvItemSelected.setText(String.valueOf(mSelectCount).concat("개"));
    }

    public void setSelectItemNumPlus() {
        mSelectCount += 1;
        mTvItemSelected.setText(String.valueOf(mSelectCount).concat("개"));
    }

    public void setSelectItemNumMinus() {
        mSelectCount -= 1;
        mTvItemSelected.setText(String.valueOf(mSelectCount).concat("개"));
    }

    private void getProduct(String keyWord) {
        //fragment에서 ProgressDialog 보이기
        ((MainActivity) getActivity()).showProgressDialog();
        final HomeService homeService = new HomeService(this, keyWord, mLastTurn);
        homeService.getProducts();
    }

    @Override
    public void getProductSuccess(List<Result> resultList) {
        ((MainActivity) getActivity()).hideProgressDialog();
        for (Result result : resultList) {
            mHomeItemList.add(new Home_Item(result.getImageUrl(), result.getpName(), result.getOdaPrice(), false, result.getpNum()));
        }

        mHomeAdapter.notifyDataSetChanged();
        mTvNothing.setVisibility(View.GONE);
        mChboxSelectAll.setClickable(true);

        mTvSelectAll.setTextColor(getActivity().getResources().getColor(R.color.catering));
    }

    @Override
    public void getProductFailure(String message) {
        ((MainActivity) getActivity()).hideProgressDialog();
        ((MainActivity) getActivity()).showCustomToast(message);
    }
}
