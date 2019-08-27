package my.project.project_oda.src.main.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.main.adapters.RecentAdapter;
import my.project.project_oda.src.main.models.Recent_Item;

public class Fragment_recent extends Fragment {

    ArrayList<Recent_Item>array_recent;
    ListView mlv_recent_keyword;
    Context mContext;
    RecentAdapter recentAdapter;
    ViewPager viewPager;

    public Fragment_recent(Context context){
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recent, container, false);

        array_recent = new ArrayList<>();
        mlv_recent_keyword = view.findViewById(R.id.lv_search_recent);
        recentAdapter = new RecentAdapter(mContext, array_recent);
        mlv_recent_keyword.setAdapter(recentAdapter);

        //dummy info
        addKeyword("고추장");

        mlv_recent_keyword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });


        return  view;
    }

    public void addKeyword(String keyword){
        array_recent.add(new Recent_Item(keyword));
        recentAdapter.notifyDataSetChanged();
    }


}
