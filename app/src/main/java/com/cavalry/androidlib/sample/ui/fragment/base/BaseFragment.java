package com.cavalry.androidlib.sample.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cavalry.androidlib.sample.mvp.presenter.TagPresenter;
import com.cavalry.androidlib.ui.fragment.LibBaseFragment;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public abstract class BaseFragment extends LibBaseFragment {
    protected TagPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter = null;
        }
    }
}
