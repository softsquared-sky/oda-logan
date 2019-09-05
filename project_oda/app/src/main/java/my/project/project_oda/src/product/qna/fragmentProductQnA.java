package my.project.project_oda.src.product.qna;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.project.project_oda.R;

public class fragmentProductQnA extends Fragment {

    Context mContext;

    public fragmentProductQnA(Context context){
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_qna, container, false);

        return  view;
    }

}
