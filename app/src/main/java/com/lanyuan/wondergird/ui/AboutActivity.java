package com.lanyuan.wondergird.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lanyuan.wondergird.R;
import com.lanyuan.wondergird.action.Util;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.button_open)
    Button button_open;

    @BindView(R.id.button_update)
    Button button_update;

    @OnClick(R.id.button_open)
    public void buttonOpenClick(View view) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("向开源致敬");
        ab.setMessage(R.string.open_chart);
        ab.setPositiveButton("确定", null);
        ab.show();
    }
    @OnClick(R.id.button_update)
    public void buttonUpdateClick(View view){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("历史更新记录");
        ab.setMessage(R.string.update_info);
        ab.setPositiveButton("确定", null);
        ab.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于  " + Util.getVersionName(this));

        ButterKnife.bind(this);
    }
}
