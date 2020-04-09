package com.xiangxue.alvin.flowlayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by zhuliang on 2019/12/2.
 */

@SuppressLint("ViewConstructor")
public class MyTextView extends View {
    private Context context;

    public MyTextView(Context context) {
        super(context);
        this.context = context;
        beginScale(R.anim.anim_out);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        beginScale(R.anim.anim_out);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        beginScale(R.anim.anim_out);
    }

    private void beginScale(int animation) {
        Animation an = AnimationUtils.loadAnimation(context, animation);
        an.setDuration(500);
        an.setFillAfter(true);
        this.startAnimation(an);
    }

}
