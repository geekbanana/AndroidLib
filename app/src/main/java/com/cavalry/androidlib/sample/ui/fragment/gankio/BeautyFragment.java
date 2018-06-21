package com.cavalry.androidlib.sample.ui.fragment.gankio;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.TypeReference;
import com.cavalry.androidlib.sample.R;
import com.cavalry.androidlib.sample.bean.gankio.BeautyBean;
import com.cavalry.androidlib.sample.bean.gankio.base.GankioBaseBean;
import com.cavalry.androidlib.sample.mvp.presenter.TagPresenter;
import com.cavalry.androidlib.sample.toolbox.manager.ApiManager;
import com.cavalry.androidlib.sample.ui.adapter.gankio.BeautyAdapter;
import com.cavalry.androidlib.sample.ui.fragment.gankio.base.GankioBaseFragment;
import com.cavalry.androidlib.toolbox.exception.LibException;
import com.cavalry.androidlib.toolbox.utils.LibToastUtils;
import com.cavalry.androidlib.view.stateview.helper.VaryViewHelperController;
import com.cavalry.androidlib.view.stateview.view.PageStateLayout;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * gankio福利页面
 *
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class BeautyFragment extends GankioBaseFragment {
    private final String TAG = "BeautyFragment";

    @Bind(R.id.rv_beauty)
    RecyclerView rvBeauty;

    private final static int TAG_BEAUTY = 1;

    private StaggeredGridLayoutManager layoutManager;
    private BeautyAdapter mBeautyAdapter;
    private VaryViewHelperController controller;
    private ViewGroup mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRLMode(PtrFrameLayout.Mode.BOTH);
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_beauty, container, false);
        return mRootView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new TagPresenter(getContext(),this);
    }

    @Override
    public void initView() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        else
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvBeauty.setLayoutManager(layoutManager);
        mBeautyAdapter = new BeautyAdapter(this, null);
        rvBeauty.setAdapter(mBeautyAdapter);

        controller = new VaryViewHelperController(rvBeauty);
    }

    @Override
    public void loadData() {
        mPresenter.getDataCache(
                assembleUrl(ApiManager.GANKIO_BEAUTY,PAGE_SIZE+"",++mPageNo+""),
                TAG_BEAUTY,
                new TypeReference<GankioBaseBean<List<BeautyBean>>>(){}.getType());
    }

    @Override
    public Map<String, String> getParams(int tag) {
        return null;
    }

    @Override
    protected View getRLView() {
        return rvBeauty;
    }

    @Override
    public void success(Object bean, int tag) {
        if(tag==TAG_BEAUTY){
            if(isRefreshing()){
                Log.e(TAG," setNewData");
                mBeautyAdapter.setNewData((List<BeautyBean>) bean);
            }else{
                Log.e(TAG," addData");
                mBeautyAdapter.addData((List<BeautyBean>) bean);
            }
            controller.restore();
        }
    }

    @Override
    public void error(Throwable e, int tag) {
        if(e instanceof LibException){
            LibException exception = (LibException) e;
            LibToastUtils.toast(exception.getMessage());
        }else{
            if(tag==TAG_BEAUTY){
                LibToastUtils.toast("网络错误");
                controller.showState(PageStateLayout.PageState.ERROR, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autoRefresh();
                    }
                });
            }

        }
    }

    @Override
    protected void onRefreshStart(PtrFrameLayout frame) {
        Log.e(TAG,"Bea  onRefreshStart isRefreshingOrLoadingMore = " + isRefreshingOrLoadingMore());

        super.onRefreshStart(frame);
        mPresenter.getData(
                assembleUrl(ApiManager.GANKIO_BEAUTY,PAGE_SIZE+"",++mPageNo+""),
                TAG_BEAUTY,
                new TypeReference<GankioBaseBean<List<BeautyBean>>>(){}.getType());
    }

    @Override
    protected void onLoadMoreStart(PtrFrameLayout frame) {
        loadData();
    }
}
