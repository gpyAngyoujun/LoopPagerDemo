package com.jimmy.looppager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayDeque;
import java.util.Queue;


public abstract class LoopPagerAdapter extends PagerAdapter {

    private final int PAGER_OFFSET; // left+offset, right+offset,start Pos
    private Queue<Object> mQueue = new ArrayDeque<>();

    protected LoopPagerAdapter() {
        PAGER_OFFSET = onInitPagerOffset();
    }

    @Override
    public final int getCount() {
        int itemCount = getItemCount();
        return itemCount > 0 ? itemCount + (PAGER_OFFSET * 2) : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = onCreateView(container, mQueue.poll(), getRealPosition(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mQueue.add(object);
    }

    public final int getOffset() {
        return PAGER_OFFSET;
    }

    /* 返回从o开始的index 0 <= val <= getItemCount()-1 */
    public final int getRealPosition(int position) {
        return getMapPosition(position) - PAGER_OFFSET;
    }

    /* 返回loop区间的index就是 0+offset <= val <= (getItemCount()-1)+offset */
    public int getMapPosition(int position) {
        int itemCount = getItemCount();

        if (itemCount <= 0) {
            return -1;
        }

        while (position < PAGER_OFFSET) {
            // 前半部分的区间
            position += itemCount;
        }

        while (position >= (PAGER_OFFSET + itemCount)) {
            // 后半部分的区间
            position -= itemCount;
        }
        return position;
    }

    /* 偏移的pager数 */
    protected int onInitPagerOffset() {
        return 3;
    }

    public abstract int getItemCount();

    protected abstract View onCreateView(ViewGroup container, Object obj, int position);
}
