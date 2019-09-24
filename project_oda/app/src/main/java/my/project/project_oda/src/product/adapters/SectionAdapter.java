package my.project.project_oda.src.product.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

import my.project.project_oda.src.main.home.fragments.FragmentHome;
import my.project.project_oda.src.product.detail.fragmentProductDetail;
import my.project.project_oda.src.product.qna.fragmentProductQnA;
import my.project.project_oda.src.product.review.FragmentProductReview;

public class SectionAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void replaceFragment(Fragment fragment, String title, int index){
        mFragmentList.set(index,fragment);
        mFragmentTitleList.set(index,title);
    }

    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public SectionAdapter(FragmentManager fm) {
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
        if (object instanceof fragmentProductDetail) {
            return 0;
        } else if (object instanceof FragmentProductReview) {
            return 1;
        } else if (object instanceof fragmentProductQnA) {
            return 2;
        }
        return POSITION_NONE;
        //else return super.getItemPosition(object);
    }
}
