package com.cavalry.androidlib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cavalry.androidlib.mvp.view.IView;
import com.cavalry.androidlib.ui.inter.IFunction;


/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public abstract class LibBaseFragment extends Fragment implements IFunction,IView{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initPresenter();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadData();
        afterOnViewCreated(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 在OnViewCreated之前执行的方法, 可以对view进行一些操作. 默认不做任何修改.
     * @param view
     * @return
     */
    protected View afterOnViewCreated(View view){
        return view;
    }
}
