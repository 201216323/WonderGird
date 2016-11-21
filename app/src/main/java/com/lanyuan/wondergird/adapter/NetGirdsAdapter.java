package com.lanyuan.wondergird.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanyuan.wondergird.R;
import com.lanyuan.wondergird.ui.PictureViewActivity;

import java.util.List;
import java.util.Map;

public class NetGirdsAdapter extends BaseAdapter {

    private List<Map<String, Uri>> draweeViewList;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public NetGirdsAdapter(Activity activity, Context context, List<Map<String, Uri>> draweeViewsList) {
        this.context = context;
        this.draweeViewList = draweeViewsList;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return draweeViewList.size();
    }

    @Override
    public Object getItem(int position) {
        return draweeViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Girds {
        SimpleDraweeView view_left;
        SimpleDraweeView view_right;

        public Girds(SimpleDraweeView view_left, SimpleDraweeView view_right) {
            this.view_left = view_left;
            this.view_right = view_right;
        }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Girds girds = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_net_girds, null);
            girds = new Girds((SimpleDraweeView) convertView.findViewById(R.id.view_left), (SimpleDraweeView) convertView.findViewById(R.id.view_right));
            convertView.setTag(girds);
        } else {
            girds = (Girds) convertView.getTag();
        }
        girds.view_left.setImageURI(draweeViewList.get(position).get("left"));
        girds.view_right.setImageURI(draweeViewList.get(position).get("right"));
        girds.view_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureViewActivity.class);
                String s = draweeViewList.get(position).get("target_left").toString();
                intent.putExtra("url",s);
                activity.startActivity(intent);
            }
        });
        girds.view_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureViewActivity.class);
                String s = draweeViewList.get(position).get("target_right").toString();
                intent.putExtra("url",s);
                activity.startActivity(intent);
            }
        });
        return convertView;
    }
}
