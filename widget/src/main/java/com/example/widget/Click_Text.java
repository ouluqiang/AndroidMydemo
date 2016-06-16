package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class Click_Text extends LinearLayout{

    private Context mContext;
    private TextView text;

    public Click_Text(Context context) {
        super(context);
        mContext=context;
        getView();
    }

    public Click_Text(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        getView();
    }

    public Click_Text(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        getView();
    }

    private void getView(){
        View view=inflate(mContext, R.layout.onclick_layout,this);
        text = (TextView) view.findViewById(R.id.text);
    }

}
