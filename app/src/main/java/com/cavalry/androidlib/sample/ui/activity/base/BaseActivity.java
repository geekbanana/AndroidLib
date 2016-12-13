package com.cavalry.androidlib.sample.ui.activity.base;

import android.os.Bundle;

import com.cavalry.androidlib.mvp.presenter.BasePresenter;
import com.cavalry.androidlib.ui.activity.LibBaseActivity;

import java.util.Map;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */
public abstract class BaseActivity<P extends BasePresenter> extends LibBaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null)
            mPresenter.detachView();
    }


}
