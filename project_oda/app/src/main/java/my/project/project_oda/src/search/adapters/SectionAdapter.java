package my.project.project_oda.src.search.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import my.project.project_oda.src.search.fragments.fragmentPopular;
import my.project.project_oda.src.search.fragments.fragmentRecent;

public class SectionAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

    public SectionAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object instanceof fragmentRecent){
            return 0;
        }else if(object instanceof fragmentPopular){
            return 1;
        }
        return POSITION_NONE;
        //else return super.getItemPosition(object);
    }
}
