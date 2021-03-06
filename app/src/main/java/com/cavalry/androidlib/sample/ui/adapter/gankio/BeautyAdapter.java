package com.cavalry.androidlib.sample.ui.adapter.gankio;


import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.cavalry.androidlib.sample.R;
import com.cavalry.androidlib.sample.bean.gankio.BeautyBean;
import com.cavalry.androidlib.toolbox.imageloader.ImageLoaderHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class BeautyAdapter extends BaseQuickAdapter<BeautyBean,BaseViewHolder> {
    private Fragment mFragment;

    public BeautyAdapter(Fragment fragment,List data) {
        super(R.layout.item_beauty, data);
        mFragment = fragment;
    }

    public BeautyAdapter(Fragment fragment) {
        super(R.layout.item_beauty,null);
        mFragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BeautyBean bean) {
        ImageLoaderHelper
                .getInstance()
                .loadImage(mFragment,
                        bean.url,
                        (ImageView) baseViewHolder.getView(R.id.image),
                        null);

        baseViewHolder.setText(R.id.title,bean.desc);
    }

}
