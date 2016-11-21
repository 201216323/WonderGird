package com.lanyuan.wondergird.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetPage {

    private static String pageCode = "";

    public static String getPageCode(String url) {
        Log.e("girl", "url:" + url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                .addHeader("Accept-Encoding", "deflate, sdch")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("Connection", "keep-alive")
                .addHeader("Host", "www.mm131.com")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36")
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            byte[] code = response.body().bytes();
            pageCode = new String(code, "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageCode;
    }

}
