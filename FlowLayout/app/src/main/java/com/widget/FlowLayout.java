package com.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenxin on 2016/5/23.
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 返回默认的layout params参数
     * @return
     */
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得父容器为该ViewGroup控件设置的大小
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // 该ViewGroup控件的宽和高，随着child view的不断被添加，宽和高也在不断变化
        int lineWith = 0; // getPaddingLeft()
        int lineHeight = 0;
        // child view共排列了多少行
        int rows = 1;

        for (int index = 0; index < getChildCount(); index++) {

            View child = getChildAt(index);
            // 测量child view的大小
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            LayoutParams params = (LayoutParams) child.getLayoutParams();
            // child view实际的宽高（包含margin）
            int childActualW = width + params.leftMargin + params.rightMargin;
            int childActualH = height + params.topMargin + params.bottomMargin;

            /*
             * lineWidth + child view的宽度 + ViewGroup的padding值
             *
             * 如果超出最大宽度 ， 换行
             */
            if(lineWith + childActualW + getPaddingRight() + getPaddingLeft() > specWidth) {
                lineWith = childActualW;
                rows++;
            }else {
                lineWith += childActualW;
            }
            lineHeight = rows * childActualH + getPaddingTop() + getPaddingBottom();

        }

        int measureHeight = modeHeight == MeasureSpec.EXACTLY ? specHeight : lineHeight;
        setMeasuredDimension(specWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 添加child view 不断累加width && height  起始坐标点为padding
        int lineWidth = getPaddingLeft();
        int lineHeight = getPaddingTop();
        int rows = 1;

        for(int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setBackgroundColor(Color.GREEN);

            int mWidth = childView.getMeasuredWidth();
            int mHeight = childView.getMeasuredHeight();

            FlowLayout.LayoutParams params = (LayoutParams) childView.getLayoutParams();
            int childActualWidth = mWidth + params.leftMargin + params.rightMargin;
            int childActualHeight = mHeight + params.topMargin + params.bottomMargin;


            if(lineWidth + childActualWidth > r - l) {
                lineWidth = childActualWidth + getPaddingLeft();
                rows++;
            }else {
                lineWidth += childActualWidth;
            }
            lineHeight += rows * childActualHeight;

            childView.layout(
                    lineWidth - childActualWidth + params.leftMargin,
                    lineHeight - childActualHeight + params.topMargin,
                    lineWidth - params.rightMargin,
                    lineHeight - params.bottomMargin);

            lineHeight = getPaddingTop();
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
