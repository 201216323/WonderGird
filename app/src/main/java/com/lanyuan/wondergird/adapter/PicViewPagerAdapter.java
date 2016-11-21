package com.lanyuan.wondergird.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PicViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public PicViewPagerAdapter(FragmentManager manager, List<Fragment> fragmentList) {
        super(manager);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
