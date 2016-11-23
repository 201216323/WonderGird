package com.lanyuan.wondergird.htmlparse;

import android.net.Uri;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HtmlParse {

    public static String getNextPage(String pageCode) { //找到下一页的地址
        if (pageCode == null || pageCode.equals("")) return pageCode;
        Document document = Jsoup.parse(pageCode);
        Elements elements = document.select(".page-en");
        for (Element e : elements) {
            String s = e.text();
            if (s.equals("下一页")) {
                return e.attr("href");
            }
        }
        return null;
    }

    public static int getPagesCount(String pageCode) {

        Document document = Jsoup.parse(pageCode);
        Elements elements = document.select("span.page-ch");
        if (elements.size() > 0) {
            String code = elements.get(0).text();
            String result;
            char c = code.charAt(2);
            Log.e("hey", String.valueOf(c));
            if (c <= '9' && c >= '0') {
                result = elements.get(0).text().substring(1, 3);
            } else {
                result = elements.get(0).text().substring(1, 2);
            }
            return Integer.parseInt(result);
        }
        return 0;
    }

    public static List<Uri> getAllElementTarget(String pageCode) {
        List<Uri> uriList = new ArrayList<>();
        Document document = Jsoup.parse(pageCode);
        Elements elements = document.select("dl.list-left dd a");
        for (Element e : elements) {
            uriList.add(Uri.parse(e.attr("href")));
        }

        return uriList;
    }

    public static List<Uri> getAllElements(String pageCode) {
        List<Uri> uriList = new ArrayList<>();
        Document document = Jsoup.parse(pageCode);
        Elements elements = document.select("dl.list-left img");
        for (Element e : elements) {
            String url = e.attr("src");
            url = url.replace("0.jpg", "m.jpg");
            uriList.add(Uri.parse(url));
        }

        return uriList;
    }

}
