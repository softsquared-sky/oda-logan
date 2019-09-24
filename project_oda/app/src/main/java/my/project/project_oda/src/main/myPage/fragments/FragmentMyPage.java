package my.project.project_oda.src.main.myPage.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import static my.project.project_oda.src.ApplicationClass.*;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.project.project_oda.src.main.myPage.adapters.ExpandableListAdapter;
import my.project.project_oda.R;
import my.project.project_oda.src.main.MainActivity;
import my.project.project_oda.src.main.myPage.MyPageService;
import my.project.project_oda.src.main.myPage.interfaces.MyPageActivityView;
import my.project.project_oda.src.main.myPage.models.Children;
import my.project.project_oda.src.main.myPage.models.Parent;
import my.project.project_oda.src.main.myPage.models.PayList;
import my.project.project_oda.src.main.myPage.models.Result;

public class FragmentMyPage extends Fragment implements MyPageActivityView {

    private ExpandableListView mExpandableList;
    private ArrayList<Parent> mGroupList;
    private ExpandableListAdapter mExpandableListAdapter;
    HashMap<Parent, ArrayList<Children>> mChildrenList;
    private ArrayList<Children> mChildrenArray;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

//        this.setDummyData();

        mGroupList = new ArrayList<>();
        mChildrenList = new HashMap<>();
        mChildrenArray = new ArrayList<>();
        mExpandableList = view.findViewById(R.id.elv_my_page);
        mExpandableListAdapter = new ExpandableListAdapter(getActivity(), mGroupList,mChildrenList, this);
        mExpandableList.setAdapter(mExpandableListAdapter);

        getMyPage();

        return view;
    }

    public void setDummyData(){

        String testImageChild = "http://thumbnail13.coupangcdn.com/thumbnails/remote/492x492ex/image/product/image/vendoritem/2018/10/04/3001893829/40945980-52b5-41c8-a335-3322e64fadaf.jpg";
        Children childDummy = new Children(testImageChild, "창녕군농협 황금메뚜기쌀 10kg 1개", "28900원", "/ 3개", "9/15\n도착");

        String testImage = "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg";

        Parent dummy = new Parent(getResources().getString(R.string.my_page_list_parent_header), testImage, getResources().getString(R.string.order_request_product_title_tools),
                getResources().getString(R.string.order_request_product_price_tools), getResources().getString(R.string.order_request_product_num_tools),
                getResources().getString(R.string.my_page_list_parent_delivery));

        mGroupList.add(dummy);

        mChildrenArray.add(childDummy);

        mChildrenList.put(mGroupList.get(0), mChildrenArray);

    }

    public void expandList(int position) {
        mExpandableList.expandGroup(position);
    }

    public void foldList(int position) {
        mExpandableList.collapseGroup(position);
    }

    private void getMyPage() {
        ((MainActivity) getActivity()).showProgressDialog();
        final MyPageService myPageService = new MyPageService(this);
        myPageService.getMyPage();
    }

    @Override
    public void getMyPageSuccess(List<Result> result) {

            for (int i = 0; i < result.size(); i++) {
                Result temp = result.get(i);
                PayList payList = temp.getOrderList().get(0);
                //각 부모 입력
                String originDate = temp.getPayDate();
                String date = originDate.substring(0, 4) + "/" + originDate.substring(4, 6) + "/" + originDate.substring(6, 8);
                mGroupList.add(new Parent("주문번호 ".concat(date), payList.getImageUrl(), payList.getpName(),
                        myFormatter.format(payList.getOdaPrice()).concat("원"), "/".concat(String.valueOf(payList.getAmount())).concat("개"),
                        getResources().getString(R.string.my_page_list_parent_delivery)));
                mChildrenArray = new ArrayList<>();
                //각 자식 입력
                for (int j = 1; j < temp.getOrderList().size(); j++) {
                    PayList a = temp.getOrderList().get(j);
                    Children child = new Children(a.getImageUrl(), a.getpName(), myFormatter.format(a.getOdaPrice()).concat("원"),
                            "/ ".concat(String.valueOf(a.getAmount())).concat("개"), "9/17\n도착");
                    mChildrenArray.add(child);
                }
                mChildrenList.put(mGroupList.get(i), mChildrenArray);
            }
            ((MainActivity) getActivity()).hideProgressDialog();
            mExpandableListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMyPageFailure(String message) {
        ((MainActivity) getActivity()).hideProgressDialog();
        ((MainActivity) getActivity()).showCustomToast(message);
    }
}
