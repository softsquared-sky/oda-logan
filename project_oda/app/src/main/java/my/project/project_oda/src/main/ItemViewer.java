package my.project.project_oda.src.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import my.project.project_oda.R;
import my.project.project_oda.src.main.models.Home_Item;

public class ItemViewer extends LinearLayout {

    ImageView iv_item;
    TextView tv_title;
    TextView tv_price;

    public ItemViewer(Context context){
        super(context);
        init(context);
    }

    public ItemViewer(Context context, @Nullable AttributeSet attrs){
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.home_list_item,this,true);

        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_price = (TextView)findViewById(R.id.tv_price);
        iv_item = (ImageView) findViewById(R.id.iv_item);
    }

    public void setItem(Home_Item home_item){
        tv_title.setText(home_item.getTitle());
        tv_price.setText(home_item.getPrice()+"원");
        //iv_item.setImageResource(home_item.getImageUrl());

    }

}
