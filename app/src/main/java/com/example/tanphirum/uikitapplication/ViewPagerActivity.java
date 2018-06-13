package com.example.tanphirum.uikitapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tanphirum.uikitapplication.adapter.MyFragmentPagerAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mPager;
    //private MyPagerAdapter adapter;

    private MyFragmentPagerAdapter adapter;
    private static final String TAG = ViewPagerActivity.class.getSimpleName();


    private TabLayout mTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mPager = findViewById(R.id.pager);

        mTabBar = findViewById(R.id.tab_bar);

        //adapter = new MyPagerAdapter(this);
        //mPager.setOffscreenPageLimit(3);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.d(TAG, "onPageScrollStateChanged " + State);
            }
        });
        mTabBar.setupWithViewPager(mPager);

        for (int  i=0; i < adapter.getCount(); i++) {
            mTabBar.getTabAt(i).setIcon(R.mipmap.ic_launcher);
        }

    }
}
