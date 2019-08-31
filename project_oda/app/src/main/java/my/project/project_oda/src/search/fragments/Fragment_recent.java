package my.project.project_oda.src.search.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.search.adapters.RecentAdapter;
import my.project.project_oda.src.search.models.Recent_Item;

import static my.project.project_oda.src.ApplicationClass.sSharedPreferences;

public class Fragment_recent extends Fragment {

    ArrayList<Recent_Item>array_recent;
    ListView mlv_recent_keyword;
    Context mContext;
    RecentAdapter recentAdapter;
    ViewPager viewPager;
    Gson gson;

    public Fragment_recent(Context context){
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recent, container, false);

        array_recent = new ArrayList<>();

        gson = new Gson();
        String json = sSharedPreferences.getString("recent", "");
        Type type = new TypeToken<ArrayList<Recent_Item>>(){
        }.getType();
        if(gson.fromJson(json,type) != null){
            array_recent = gson.fromJson(json,type);
        }else{
        }

        mlv_recent_keyword = view.findViewById(R.id.lv_search_recent);
        recentAdapter = new RecentAdapter(mContext, array_recent);
        mlv_recent_keyword.setAdapter(recentAdapter);
        mlv_recent_keyword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recent_Item item = (Recent_Item) parent.getAdapter().getItem(position);

            }
        });
        return  view;
    }

    public void addKeyword(String keyword){

        //이전에 검색한 적 있으면 지우고 최상단으로 올림
        removeKeyword(keyword);
        array_recent.add(0,new Recent_Item(keyword));
        recentAdapter.notifyDataSetChanged();
    }

    public void removeKeyword(String keyword){
        for( int i=0; i<array_recent.size(); i++){
            if(array_recent.get(i).getKeyword().equals(keyword)){
                array_recent.remove(i);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        String json = gson.toJson(array_recent);
        editor.putString("recent", json);
        editor.apply();
    }
}
