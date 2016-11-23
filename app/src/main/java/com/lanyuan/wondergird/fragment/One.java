package com.lanyuan.wondergird.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanyuan.wondergird.action.GetDataTask;
import com.lanyuan.wondergird.action.GetMoreDataTask;
import com.lanyuan.wondergird.adapter.NetGirdsAdapter;
import com.lanyuan.wondergird.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class One extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.net_girds)
    public PullToRefreshListView mlistView;

    public String nextUrl = "";
    public String url;
    public NetGirdsAdapter madapter;
    public List<Map<String, Uri>> mdata;
    public One one;

    private int scrollX = 0;
    private int scrollY = 0;
    private int position;

    private View mFragmentView;
    //控件是否已经初始化
    private boolean isCreateView = false;
    //是否已经加载过数据
    private boolean isLoadData = false;

    private String mParam1;
    private String mParam2;

    public One() {
    }

    public static One newInstance(String param1, String param2) {
        One fragment = new One();
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

    public void init(String url) {
        this.url = url;
        //Log.e("hey", this.url);
    }

    @Override
    public void onStart() { //在onStart方法里面进行fragment的组件初始化而不能在onCreate方法里
        super.onStart();
        one = this;
        if (mdata == null) mdata = new ArrayList<>();
        if (madapter == null)
            madapter = new NetGirdsAdapter(getActivity(), getContext(), mdata);
        if (mdata.size() < 1) {
            new GetDataTask(one).execute();
            mlistView.setMode(PullToRefreshBase.Mode.BOTH);
        } else if (mdata.size() > 1) {
            mlistView.setAdapter(madapter);
            mlistView.getRefreshableView().setSelection(position);
            mlistView.setMode(PullToRefreshBase.Mode.BOTH);
        }
    }

    private void initListView() {
        mlistView.setScrollingWhileRefreshingEnabled(false);
        mlistView.setVerticalFadingEdgeEnabled(false);
        mlistView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mlistView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    ListView v = mlistView.getRefreshableView();
                    position = v.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        mlistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() { //设置上拉刷新和下拉加载更多的监听器
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mdata.removeAll(mdata);
                new GetDataTask(one).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetMoreDataTask(one).execute();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //fragment加载布局文件的地方
        mFragmentView = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, mFragmentView);
        initListView();
        return mFragmentView;
    }
}
