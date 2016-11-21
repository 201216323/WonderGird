package com.lanyuan.wondergird.action;

import com.lanyuan.wondergird.htmlparse.HtmlParse;
import com.lanyuan.wondergird.network.GetPage;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> getAllPages(String firstUrl) {
        List<String> list = new ArrayList<>();

        int count = HtmlParse.getPagesCount(GetPage.getPageCode(firstUrl));
        //Log.e("url", firstUrl);
        String keyCode = firstUrl.substring(firstUrl.length() - 9, firstUrl.length() - 5);
        String preCode = "http://img1.mm131.com/pic/";
        for (int i = 1; i <= count; i++) {
            list.add(preCode + keyCode + "/" + i + ".jpg");
            //Log.e("url", preCode + keyCode + "/" + i + ".jpg");
        }

        return list;
    }

}
