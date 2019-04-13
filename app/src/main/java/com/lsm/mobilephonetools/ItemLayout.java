package com.lsm.mobilephonetools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2019/4/12 16:54
 */
public class ItemLayout extends FrameLayout {


    private Context mContext;
    //左边文字
    private String mMiddleText;
    private Drawable mDrawable;


    public ItemLayout(Context context) {
        this(context, null);
    }

    public ItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ItemLayout);
        mMiddleText = array.getString(R.styleable.ItemLayout_middleText);
        mDrawable = array.getDrawable(R.styleable.ItemLayout_MiddleIconDrawable);
        array.recycle();
        addView();
        init();
    }

    private void init() {

    }


    private void addView() {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, this, false);
        this.addView(itemView);
    }
}
