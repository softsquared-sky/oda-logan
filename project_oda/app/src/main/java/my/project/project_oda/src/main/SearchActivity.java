package my.project.project_oda.src.main;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import my.project.project_oda.R;
import my.project.project_oda.src.main.Fragment.Fragment_popular;
import my.project.project_oda.src.main.Fragment.Fragment_recent;
import my.project.project_oda.src.main.adapters.SectionAdapter;

public class SearchActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    Context mContext;
    private ViewPager mViewPager;
    EditText mEdt_keyword;
    SectionAdapter madapter = new SectionAdapter(getSupportFragmentManager());
    Fragment_recent mFr;
    Fragment_popular mFp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initialize();
        this.setListener();
        setupViewPager(mViewPager);

        tabLayout = findViewById(R.id.search_tab);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search_back:
                finish();
                break;
        }
    }

    public void initialize(){

        mContext = this;
        mFr = new Fragment_recent(mContext);
        mFp = new Fragment_popular();
        mViewPager = findViewById(R.id.search_container);
        mEdt_keyword = findViewById(R.id.edt_search_keyword);

    }

    public void setupViewPager(ViewPager viewPager){
        madapter.addFragment(mFr, "최근검색어");
        madapter.addFragment(mFp, "인기검색어");
        viewPager.setAdapter(madapter);
    }

    public void setListener(){

        mEdt_keyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    //Toast.makeText(getApplicationContext(), mEdt_keyword.getText().toString(), Toast.LENGTH_SHORT).show();
                    if(tabLayout.getSelectedTabPosition() == 0){
                        //최근검색어 탭일 경우
                        if(!mEdt_keyword.getText().toString().equals("")) {
                            mFr.addKeyword(mEdt_keyword.getText().toString());
                        }
                        //Toast.makeText(getApplicationContext(), mEdt_keyword.getText().toString(), Toast.LENGTH_SHORT).show();
                        refresh();
                    }else if(madapter.getItemPosition(mViewPager) == 1){
                        //인기검색어 탭일 경우
                    }
                }
                return false;
            }
        });

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            switch (position){
                case 1:

            }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void refresh(){
        madapter.notifyDataSetChanged();
    }
}
