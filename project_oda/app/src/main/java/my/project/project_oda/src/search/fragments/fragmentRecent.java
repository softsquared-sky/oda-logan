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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import my.project.project_oda.R;
import my.project.project_oda.src.search.adapters.RecentAdapter;
import my.project.project_oda.src.search.models.Recent_Item;

import static my.project.project_oda.src.ApplicationClass.sSharedPreferences;

public class fragmentRecent extends Fragment {

    private ArrayList<Recent_Item> mRecentList;
    //private ListView ivRecentKeyword;
    private Context mContext;
    private RecentAdapter mRecentAdapter;
    private Gson mGson;

    public fragmentRecent(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recent, container, false);

        ListView ivRecentKeyword;
        mRecentList = new ArrayList<>();

        mGson = new Gson();
        String json = sSharedPreferences.getString("recent", "");
        Type type = new TypeToken<ArrayList<Recent_Item>>() {
        }.getType();
        if (mGson.fromJson(json, type) != null) {
            mRecentList = mGson.fromJson(json, type);
        }

        ivRecentKeyword = view.findViewById(R.id.lv_search_recent);
        mRecentAdapter = new RecentAdapter(mContext, mRecentList);
        ivRecentKeyword.setAdapter(mRecentAdapter);
        ivRecentKeyword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recent_Item item = (Recent_Item) parent.getAdapter().getItem(position);

            }
        });
        return view;
    }

    public void addKeyword(String keyword) {

        //이전에 검색한 적 있으면 지우고 최상단으로 올림
        removeKeyword(keyword);
        mRecentList.add(0, new Recent_Item(keyword));
        mRecentAdapter.notifyDataSetChanged();
    }

    public void removeKeyword(String keyword) {
        for (int i = 0; i < mRecentList.size(); i++) {
            if (mRecentList.get(i).getKeyword().equals(keyword)) {
                mRecentList.remove(i);
                break;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        String json = mGson.toJson(mRecentList);
        editor.putString("recent", json);
        editor.apply();
    }
}
