package com.lanyuan.wondergird.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanyuan.wondergird.R;

public class Pic extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String url;

    private String mParam1;
    private String mParam2;

    public Pic() {
    }

    public void initUrl(String url) {
        this.url = url;
    }

    public static Pic newInstance(String param1, String param2) {
        Pic fragment = new Pic();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        initView();
    }

    private void initView() {
        SimpleDraweeView view = (SimpleDraweeView) getView().findViewById(R.id.pic_view);
        view.setImageURI(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pic, container, false);
    }

}
