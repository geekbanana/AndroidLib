package com.cavalry.androidlib.ui.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cavalry.androidlib.mvp.view.IView;
import com.cavalry.androidlib.ui.inter.IFunction;

import butterknife.ButterKnife;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */
public abstract class LibBaseActivity extends AppCompatActivity implements IView,IFunction {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initPresenter();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(getBaseContext()).inflate(layoutResID, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        setContentView(view,view.getLayoutParams());
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(beforeSetContentView(view), params);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 在setContentView之前执行的方法, 可以对view进行一些操作. 默认不做任何修改.
     * @param view
     * @return
     */
    protected View beforeSetContentView(View view){
        return view;
    }
}
