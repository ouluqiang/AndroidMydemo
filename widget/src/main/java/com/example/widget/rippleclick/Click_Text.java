package com.example.widget.rippleclick;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.widget.R;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class Click_Text extends LinearLayout{

    private Context mContext;
    private RippleButton rippleButton;
    private float textsize;
    private int textbackgroud;
    private String text;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private OnClickListener onClickListener;

    public Click_Text(Context context) {
        super(context);
        mContext=context;
        getView();
    }

    public Click_Text(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        getStyle(attrs);
        getView();
    }

    public Click_Text(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        getView();
    }

    private void getStyle(AttributeSet attrs){
        TypedArray typedArray=mContext.obtainStyledAttributes(attrs,R.styleable.Click_Text);
        textsize = typedArray.getDimension(R.styleable.Click_Text_textSize,18);
        textbackgroud = typedArray.getColor(R.styleable.Click_Text_textbackground,0xff12B7F5);
        text = typedArray.getString(R.styleable.Click_Text_text);
        typedArray.recycle();
    }

    private void getView(){
        View view=inflate(mContext, R.layout.onclick_layout,this);
        rippleButton = (RippleButton) view.findViewById(R.id.rippleButton);
        rippleButton.setText(text);
        rippleButton.setBackgroundColor(textbackgroud);
        rippleButton.setTextSize(textsize);
        rippleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.ClickListener();
            }
        });
    }

    public interface OnClickListener{
        public void ClickListener();
    }


}
