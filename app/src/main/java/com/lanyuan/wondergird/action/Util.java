package com.lanyuan.wondergird.action;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.lanyuan.wondergird.htmlparse.HtmlParse;
import com.lanyuan.wondergird.network.GetPage;
import com.lanyuan.wondergird.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static void goToAppMarket(Context context) {
        if (isExistKuAnMarket(context)) {
            Toast.makeText(context, "本应用目前仅发布在酷安市场，请前往酷安市场评分……", Toast.LENGTH_SHORT).show();
            try {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "跳转出错……请向作者反馈", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "未检测到酷安市场客户端，正在跳转到网页……", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.coolapk.com/apk/com.lanyuan.wondergird"));
            context.startActivity(intent);
        }
    }

    public static boolean isExistKuAnMarket(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> infoList = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (infoList != null) {
            for (int i = 0; i < infoList.size(); i++) {
                pName.add(infoList.get(i).packageName);
            }
        }
        return pName.contains("com.coolapk.market");
    }

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
