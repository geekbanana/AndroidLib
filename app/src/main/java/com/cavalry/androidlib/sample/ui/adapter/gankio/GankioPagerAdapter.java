package com.cavalry.androidlib.sample.ui.adapter.gankio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.cavalry.androidlib.sample.ui.fragment.gankio.BeautyFragment;
import com.cavalry.androidlib.ui.adapter.TitleFragmentPagerAdapter;

import java.util.List;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class GankioPagerAdapter extends TitleFragmentPagerAdapter {

    public GankioPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public GankioPagerAdapter(FragmentManager fm, List<String> pageTitleList) {
        super(fm, pageTitleList);
    }

    @Override
    public Fragment getItem(int position) {
        return new BeautyFragment();
    }
}
