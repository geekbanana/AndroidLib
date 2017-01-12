package com.cavalry.androidlib.sample.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cavalry.androidlib.sample.BuildConfig;
import com.cavalry.androidlib.sample.mvp.presenter.TagPresenter;
import com.cavalry.androidlib.ui.fragment.LibBaseFragment;
import com.cavalry.androidlib.ui.fragment.LibRLBaseFragment;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * <p>1.具有refresh/loadmore功能, 默认为关闭状态, 子类通过{@link #setRLMode(PtrFrameLayout.Mode)}设置</p>
 * <p>2.带有页码mPageNo. 在{@link #onRefreshStart(PtrFrameLayout)}时自动mPageNo=0;
 *      在{@link #onError(Throwable, int)}中, 若isLoadingMore(), 自动 --mPageNo</p>
 * <p>3.在{@link #success(Object, int)}之后自动{@link #refreshOrLoadMoreComplete()}</p>
 * <p>4.在{@link #error(Throwable, int)}之前自动{@link #refreshOrLoadMoreComplete()}</p>
 *
 * @author Cavalry Lin
 * @since 1.0.0
 */

public abstract class BaseFragment extends LibRLBaseFragment {
    protected TagPresenter mPresenter;
    protected final int PAGE_SIZE = 20;
    protected int mPageNo = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRLMode(PtrFrameLayout.Mode.NONE);//设置位没有refresh/loadmore功能, 这样就不会初始化RL控件. 子类可重新设置RL功能
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter = null;
        }
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        success(bean,tag);

        afterSuccess(bean, tag);
    }

    private void afterSuccess(Object bean, int tag) {
        if(isRefreshingOrLoadingMore()){
            refreshOrLoadMoreComplete();
        }
    }


    @Override
    public void onError(Throwable e, int tag) {
        beforeError(e,tag);
        error(e,tag);
    }

    private void beforeError(Throwable e, int tag) {
        if(BuildConfig.DEBUG){
            e.printStackTrace();
        }
        if (isLoadingMore() && mPageNo>0){
            --mPageNo;
        }
        if(isRefreshingOrLoadingMore()){
            refreshOrLoadMoreComplete();
        }
    }

    protected abstract void success(Object bean, int tag);

    protected abstract void error(Throwable e, int tag);

    //RL的几个方法覆写为非抽象
    /**
     * {@inheritDoc}
     * @return
     */
    protected View getRLView(){return null;}

    /**
     * 已将mPageNo置为0
     * @return
     */
    protected void onRefreshStart(PtrFrameLayout frame){
        mPageNo = 0;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    protected void onLoadMoreStart(PtrFrameLayout frame){}
}
