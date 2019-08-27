package my.project.project_oda.src.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import my.project.project_oda.R;
import my.project.project_oda.src.main.models.Home_Item;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_home_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;

        }
    }

    class ItemAdapter extends BaseAdapter{
        ArrayList<Home_Item> items = new ArrayList<Home_Item>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Home_Item item){
            items.add(item);
        }

        @Override
        public Home_Item getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ItemViewer itemViewer = new ItemViewer(getApplicationContext());
            itemViewer.setItem(items.get(i));
            return itemViewer;
        }
    }

}
