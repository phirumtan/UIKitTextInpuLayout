package com.example.tanphirum.uikitapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tanphirum.uikitapplication.fragment.EmptyFragment;
import com.example.tanphirum.uikitapplication.fragment.MainFragment;
import com.example.tanphirum.uikitapplication.fragment.RegisterFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RegisterFragment();
            case 1:
                return new MainFragment();
            default:
                return new EmptyFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
