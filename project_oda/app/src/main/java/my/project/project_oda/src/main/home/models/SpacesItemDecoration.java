package my.project.project_oda.src.main.home.models;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{

   private int mSpace;

    public SpacesItemDecoration(int mSpace){
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        if(parent.getChildLayoutPosition(view) == 0)
            outRect.top = mSpace;
        else outRect.top = 0;
    }
}