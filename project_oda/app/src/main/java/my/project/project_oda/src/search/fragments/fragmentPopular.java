package my.project.project_oda.src.search.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import my.project.project_oda.R;
import my.project.project_oda.src.search.SearchActivity;
import my.project.project_oda.src.search.SearchService;
import my.project.project_oda.src.search.adapters.PopularAdapter;
import my.project.project_oda.src.search.interfaces.SearchActivityView;
import my.project.project_oda.src.search.models.PopularItem;

public class fragmentPopular extends Fragment implements SearchActivityView {

    private ArrayList<PopularItem> mPopularList;
    ListView mLvPopular;
    private PopularAdapter mPopularAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_popular, container, false);

        mPopularList = new ArrayList<>();
        mLvPopular = view.findViewById(R.id.lv_search_popular);
        mPopularAdapter = new PopularAdapter(getActivity(), mPopularList);
        mLvPopular.setAdapter(mPopularAdapter);

        getPopular();

        return  view;
    }

    private void getPopular(){
        ((SearchActivity)getActivity()).showProgressDialog();
        final SearchService searchService = new SearchService(this);
        searchService.getPopular();
    }

    @Override
    public void getPopularSuccess(List<PopularItem> list) {
        ((SearchActivity)getActivity()).hideProgressDialog();
        for(PopularItem item : list){
            mPopularList.add(item);
        }
        mPopularAdapter.notifyDataSetChanged();

    }

    @Override
    public void getPopularFailure(String message) {

    }
}
