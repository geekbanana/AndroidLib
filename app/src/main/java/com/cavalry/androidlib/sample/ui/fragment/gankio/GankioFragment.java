package com.cavalry.androidlib.sample.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cavalry.androidlib.sample.R;
import com.cavalry.androidlib.sample.ui.adapter.gankio.GankioPagerAdapter;
import com.cavalry.androidlib.sample.ui.fragment.gankio.base.GankioBaseFragment;

import java.util.Arrays;
import java.util.Map;


/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class GankioFragment extends GankioBaseFragment {

    TabLayout tabLayout;
    ViewPager viewPager;


    private String[] mTabs;
    private View mBaseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_gankio, container,false);
        return mBaseView;
    }

    @Override
    public void initData() {
        mTabs = new String[]{"福利","Android","iOS","休息视频","拓展资源","前端","all"};
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) mBaseView.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) mBaseView.findViewById(R.id.view_pager);

        GankioPagerAdapter gankioPagerAdapter = new GankioPagerAdapter(getFragmentManager(), Arrays.asList(mTabs));
        viewPager.setAdapter(gankioPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void loadData() {

    }

    @Override
    public Map<String, String> getParams(int tag) {
        return null;
    }

    @Override
    public void success(Object bean, int tag) {

    }

    @Override
    public void error(Throwable e, int tag) {

    }
}
