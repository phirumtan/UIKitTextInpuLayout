package com.example.tanphirum.uikitapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tanphirum.uikitapplication.adapter.MyFragmentPagerAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mPager;
    //private MyPagerAdapter adapter;

    private MyFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mPager = findViewById(R.id.pager);

        //adapter = new MyPagerAdapter(this);
        //mPager.setOffscreenPageLimit(3);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
    }
}
