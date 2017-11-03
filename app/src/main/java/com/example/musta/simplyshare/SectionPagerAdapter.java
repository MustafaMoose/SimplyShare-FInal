package com.example.musta.simplyshare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class SectionPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public SectionPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Music";
            case 2:
                return "Files";
            case 3:
                return "Pictures";
            case 4:
                return "Videos";
            default:
                return "Apps";
        }
    }
}