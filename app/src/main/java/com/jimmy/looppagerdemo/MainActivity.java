package com.jimmy.looppagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jimmy.looppager.LoopPagerAdapter;
import com.jimmy.looppager.LoopViewPager;

public class MainActivity extends Activity {

    private LoopPagerAdapter adapter = new LoopPagerAdapter() {
        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        protected View onCreateView(ViewGroup container, Object obj, int position) {
            ImageView view;
            if (obj == null) {
                view = new ImageView(container.getContext());
            } else {
                view = (ImageView) obj;
            }

            int res;
            if (position == 0) {
                res = R.mipmap.a_1;
            } else if (position == 1) {
                res = R.mipmap.a_2;
            } else {
                res = R.mipmap.a_3;
            }

            view.setImageResource(res);
            return view;
        }
    };

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT);

        LoopViewPager pager = (LoopViewPager) findViewById(R.id.loop_view_pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(adapter.getOffset(), false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pos = position % adapter.getItemCount();
                toast.setText("Current:" + pos);
                toast.show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
