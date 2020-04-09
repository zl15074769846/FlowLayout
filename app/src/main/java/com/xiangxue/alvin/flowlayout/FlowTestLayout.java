package com.xiangxue.alvin.flowlayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuliang on 2019/11/20.
 */

@SuppressLint("ViewConstructor")
public class FlowTestLayout extends ViewGroup {

    private int mHorizontalSpacing = dp2px(16); //每个item横向间距
    private int mVerticalSpacing = dp2px(8); //每个item垂直间距

    private List<List<View>> allLines;// 记录所有的行，一行一行的存储
    private List<Integer> heights; // 记录每一行的行高

    public FlowTestLayout(Context context) {
        super(context);
    }

    public FlowTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowTestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initMeasureParams() {
        allLines = new ArrayList<>();
        heights = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        initMeasureParams();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();
        int parentWidth = 0;
        int parentHeight = 0;

        ArrayList<View> lineView = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), params.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), params.height);
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            if (lineWidth + childView.getMeasuredWidth() + mHorizontalSpacing > selfWidth) {

                parentWidth = Math.max(parentWidth, lineWidth + mHorizontalSpacing);
                parentHeight = parentHeight + lineHeight + mVerticalSpacing;

                allLines.add(lineView);
                heights.add(lineHeight);
                lineWidth = 0;
                lineHeight = 0;
                lineView = new ArrayList<>();

            }

            lineView.add(childView);
            lineWidth = lineWidth + childView.getMeasuredWidth() + mHorizontalSpacing;
            lineHeight = Math.max(lineHeight, childView.getMeasuredHeight());


            if (i == getChildCount() - 1) {

                parentWidth = Math.max(parentWidth, lineWidth + mHorizontalSpacing);
                parentHeight = parentHeight + lineHeight + mVerticalSpacing;

                allLines.add(lineView);
                heights.add(lineHeight);
                lineWidth = 0;
                lineHeight = 0;
                lineView = new ArrayList<>();
            }


        }

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (modeWidth == MeasureSpec.EXACTLY) ? selfWidth : parentWidth;
        int realHeight = (modeHeight == MeasureSpec.EXACTLY) ? selfHeight : parentHeight;

        setMeasuredDimension(realWidth, realHeight);


    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        int curT=0;
        int curLeft=getLeft();
        for(int x=0;x<allLines.size();x++)
        {
            int lineHeight=heights.get(x);
            for(int y=0;y<allLines.get(x).size();y++)
            {
                View childView=allLines.get(x).get(y);
                int left=curLeft;
                int top=curT;
                int right=curLeft+childView.getMeasuredWidth();
                int bottom=curT+childView.getMeasuredHeight();
                childView.layout(left,top,right,bottom);
                curLeft=right+mHorizontalSpacing;
            }
            curLeft=0;
            curT=curT+lineHeight+mVerticalSpacing;
        }


    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
