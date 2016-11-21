package com.lanyuan.wondergird.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanyuan.wondergird.R;
import com.lanyuan.wondergird.action.Util;
import com.lanyuan.wondergird.adapter.PicViewPagerAdapter;
import com.lanyuan.wondergird.fragment.Pic;

import java.util.ArrayList;
import java.util.List;

public class PictureViewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PicViewPagerAdapter pagerAdapter;
    private List<Fragment> fragmentList;
    private Handler handler;

    private String url;
    private SimpleDraweeView mDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        mDraweeView = (SimpleDraweeView) findViewById(R.id.pic_view);

        init();
    }

    private void initFragment(List<String> list) {
        viewPager = (ViewPager) findViewById(R.id.pic_viewpager);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Pic p = new Pic();
            p.initUrl(list.get(i));
            fragmentList.add(p);
        }

        pagerAdapter = new PicViewPagerAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);
    }

    private void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        if (url == null) {
            Toast.makeText(PictureViewActivity.this, "出错了", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //Toast.makeText(PictureViewActivity.this, String.valueOf(msg.obj), Toast.LENGTH_SHORT).show();
                    initFragment((List<String>) msg.obj);
                }
            };
            new Thread() {
                @Override
                public void run() {
                    List<String> list = Util.getAllPages(url);
                    Message msg = new Message();
                    msg.obj = list;
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }
}
