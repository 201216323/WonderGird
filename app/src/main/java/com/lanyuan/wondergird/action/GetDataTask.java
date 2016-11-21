package com.lanyuan.wondergird.action;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.lanyuan.wondergird.adapter.NetGirdsAdapter;
import com.lanyuan.wondergird.fragment.One;
import com.lanyuan.wondergird.htmlparse.HtmlParse;
import com.lanyuan.wondergird.network.GetPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDataTask extends AsyncTask<Void, Void, Void> {

    private One fragment;

    public GetDataTask(One fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        fragment.madapter = new NetGirdsAdapter(fragment.getActivity(), fragment.getContext(), fragment.mdata);
        fragment.mlistView.setAdapter(fragment.madapter);
        fragment.mlistView.onRefreshComplete();
    }

    @Override
    protected Void doInBackground(Void... params) {
        List<Map<String, Uri>> data = new ArrayList<>();
        Log.e("hey",fragment.url);
        String pageCode = GetPage.getPageCode(fragment.url);
        fragment.nextUrl = fragment.url + HtmlParse.getNextPage(pageCode);
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
        return null;
    }
}
