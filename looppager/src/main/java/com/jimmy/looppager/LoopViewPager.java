package com.jimmy.looppager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


public class LoopViewPager extends ViewPager {

    public LoopViewPager(Context context) {
        super(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter instanceof LoopPagerAdapter) {
            super.setAdapter(adapter);
        } else {
            throw new IllegalArgumentException("the adapter must be LoopPagerAdapter!");
        }

        setOnPageChangeListener(new OnPageChangeListener() {
            private int position;

            @Override
            public void onPageScrolled(int position, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {
                if (this.position != position) {
                    this.position = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE) {
                    int mapPosition = ((LoopPagerAdapter) getAdapter()).getMapPosition(position);
                    if (mapPosition != position && mapPosition > -1) {
                        setCurrentItem(mapPosition, false);
                    }
                }
            }
        });

    }
}
