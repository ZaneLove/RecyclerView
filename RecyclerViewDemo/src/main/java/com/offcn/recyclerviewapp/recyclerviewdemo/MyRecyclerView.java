package com.offcn.recyclerviewapp.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Administrator on 2014/12/28.
 */
public class MyRecyclerView extends RecyclerView implements RecyclerView.OnScrollListener{
    private View mCurrentView; //当前View
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //滚动时回调的接口
    private OnItemScrollChangeListener mItemScrollChangeListener;
    public void setItemScrollChangeListener(OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }
    public interface OnItemScrollChangeListener {
        void onChange(View view,int position);
    }

    @Override
    public void onScrollStateChanged(int i) {

    }

    //滚动时,判断当前第一个View是否发生变化,发生才回调
    @Override
    public void onScrolled(int i, int i1) {
        View newView = getChildAt(0);
        if(mItemScrollChangeListener !=null) {
            if(newView != null && newView != mCurrentView) {
                mCurrentView = newView;
                mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //设置默认的显示第一张图片
        mCurrentView = getChildAt(0);
        if(mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
        }
    }
/*
    //因为继承了OnScrollListenenr监听事件,所以不用覆写onTouchEvent方法
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentView = getChildAt(0);
            if(mItemScrollChangeListener != null) {
                mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }*/
}