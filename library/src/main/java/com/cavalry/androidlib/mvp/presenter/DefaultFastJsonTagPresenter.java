package com.cavalry.androidlib.mvp.presenter;

import android.content.Context;

import com.cavalry.androidlib.mvp.service.DefualtServiceGenerator;
import com.cavalry.androidlib.mvp.service.GetService;
import com.cavalry.androidlib.mvp.service.PostService;
import com.cavalry.androidlib.mvp.view.IView;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class DefaultFastJsonTagPresenter extends LibFastJsonTagPresenter {

    public DefaultFastJsonTagPresenter(Context context, IView view) {
        super(context, view);
    }

    protected GetService createGetService() {
        return DefualtServiceGenerator.getInstance().createService(GetService.class);
    }

    protected PostService createPostService(){
        return DefualtServiceGenerator.getInstance().createService(PostService.class);
    }
}
