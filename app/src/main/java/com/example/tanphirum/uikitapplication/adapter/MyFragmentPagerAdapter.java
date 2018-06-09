package com.example.tanphirum.uikitapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tanphirum.uikitapplication.fragment.MyPagerFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new MyPagerFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
