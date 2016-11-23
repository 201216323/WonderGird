package com.lanyuan.wondergird.action;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lanyuan.wondergird.fragment.One;
import com.lanyuan.wondergird.htmlparse.HtmlParse;
import com.lanyuan.wondergird.network.GetPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMoreDataTask extends AsyncTask<Void, Void, Void> {
    private One fragment;
    private Handler handler;

    public GetMoreDataTask(One fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1){
                    fragment.NothingInfo();
                }
            }
        };
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        fragment.madapter.notifyDataSetChanged();
        fragment.mlistView.onRefreshComplete();
    }

    @Override
    protected Void doInBackground(Void... params) {
        List<Map<String, Uri>> data = new ArrayList<>();
        String pageCode = GetPage.getPageCode(fragment.nextUrl);
        String nextpage = HtmlParse.getNextPage(pageCode);
        if (nextpage == null) {
            Message message = new Message();
            message.what = 1;
            GetMoreDataTask.this.handler.sendMessage(message);
            return null;
        } else {
            fragment.nextUrl = fragment.url + nextpage;
            List<Uri> uriList = HtmlParse.getAllElements(pageCode);
            List<Uri> targetList = HtmlParse.getAllElementTarget(pageCode);
            int size = uriList.size() % 2 == 0 ? uriList.size() : uriList.size() - 1;
            for (int i = 0; i < size; i += 2) {
                Map<String, Uri> temp = new HashMap<>();
                temp.put("left", uriList.get(i));
                temp.put("right", uriList.get(i + 1));
                temp.put("target_left", targetList.get(i));
                temp.put("target_right", targetList.get(i + 1));
                data.add(temp);
            }
            fragment.mdata.addAll(data);
        }
        return null;
    }
}
