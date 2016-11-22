package com.lanyuan.wondergird.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lanyuan.wondergird.R;
import com.lanyuan.wondergird.adapter.ViewPagerAdapter;
import com.lanyuan.wondergird.fragment.One;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlowerActivity extends AppCompatActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.pager_flower)
    ViewPager viewPager;

    private ViewPagerAdapter pagerAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        setTitle("(^ ◕ᴥ◕ ^)");

        ButterKnife.bind(this);

        initFragment();
    }

    private void initFragment() {

        String[] urls = new String[]{"http://www.mm131.com/xinggan/", "http://www.mm131.com/qingchun/", "http://www.mm131.com/chemo/", "http://www.mm131.com/qipao/", "http://www.mm131.com/xiaohua/", "http://www.mm131.com/mingxing/"};

        fragmentList = new ArrayList<>();
        for (String s : urls) {
            One temp = new One();
            temp.init(s);
            fragmentList.add(temp);
        }

        titleList = new ArrayList<>();
        titleList.add("性感美女");
        titleList.add("清纯美女");
        titleList.add("性感车模");
        titleList.add("旗袍美女");
        titleList.add("美女校花");
        titleList.add("明星写真");

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        //tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
    }
}
