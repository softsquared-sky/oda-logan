package my.project.project_oda.src.search;

import android.content.Context;
import android.content.Intent;
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
import my.project.project_oda.src.cart.CartActivity;
import my.project.project_oda.src.search.adapters.SectionAdapter;
import my.project.project_oda.src.search.fragments.fragmentPopular;
import my.project.project_oda.src.search.fragments.fragmentRecent;

public class SearchActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    Context mContext;
    private ViewPager mViewPager;
    EditText mEdtKeyword;
    SectionAdapter madapter = new SectionAdapter(getSupportFragmentManager());
    fragmentRecent mFragmentRecent;
    fragmentPopular mFragmentPopular;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initialize();
        this.setListener();
        setupViewPager(mViewPager);

        mTabLayout = findViewById(R.id.search_tab);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.iv_search_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
        }
    }

    public void initialize() {

        mContext = this;
        mFragmentRecent = new fragmentRecent(mContext);
        mFragmentPopular = new fragmentPopular();
        mViewPager = findViewById(R.id.search_container);
        mEdtKeyword = findViewById(R.id.edt_search_keyword);

    }

    public void setupViewPager(ViewPager viewPager) {
        madapter.addFragment(mFragmentRecent, "최근검색어");
        madapter.addFragment(mFragmentPopular, "인기검색어");
        viewPager.setAdapter(madapter);
    }

    public void setListener() {

        mEdtKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Toast.makeText(getApplicationContext(), mEdt_keyword.getText().toString(), Toast.LENGTH_SHORT).show();
                    if (mTabLayout.getSelectedTabPosition() == 0) {
                        //최근검색어 탭일 경우
                        if (!mEdtKeyword.getText().toString().equals("")) {
                            mFragmentRecent.addKeyword(mEdtKeyword.getText().toString());
                        }
                        //Toast.makeText(getApplicationContext(), mEdt_keyword.getText().toString(), Toast.LENGTH_SHORT).show();
                        refresh();
                    } else if (madapter.getItemPosition(mViewPager) == 1) {
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
        switch (position) {
            case 0:
                mViewPager.setCurrentItem(0);
                break;
            case 1:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void refresh() {
        madapter.notifyDataSetChanged();
    }
}
