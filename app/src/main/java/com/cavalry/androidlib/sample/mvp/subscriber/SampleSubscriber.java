package com.cavalry.androidlib.sample.mvp.subscriber;

import com.cavalry.androidlib.mvp.subscriber.JsonSubscriber;
import com.cavalry.androidlib.sample.R;
import com.cavalry.androidlib.sample.bean.gankio.base.GankioBaseBean;
import com.cavalry.androidlib.sample.toolbox.manager.CacheManager;
import com.cavalry.androidlib.toolbox.exception.LibException;
import com.cavalry.androidlib.toolbox.utils.LibLog;
import com.cavalry.androidlib.toolbox.utils.LibResUtils;

import java.lang.reflect.Type;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public abstract class SampleSubscriber extends JsonSubscriber{
    private final static String TAG = "SampleSubscriber";

    public SampleSubscriber(Class clazz) {
        super(clazz);
    }

    public SampleSubscriber(Type typeOfT) {
        super(typeOfT);
    }

    public SampleSubscriber(Class clazz, String key, boolean cache) {
        super(clazz, key, cache);
    }

    public SampleSubscriber(Type typeOfT, String key, boolean cache) {
        super(typeOfT, key, cache);
    }


    @Override
    protected int processBean(Object obj) {
        if(obj instanceof GankioBaseBean){
            GankioBaseBean gankBaseBean = (GankioBaseBean) obj;
            if(gankBaseBean.error){
                return PROCESS_ERROR;
            }else{
                return PROCESS_SUCCESS;
            }
        }
        return PROCESS_OTHER;
    }

    @Override
    protected void processCache(String key, String json, boolean cache) {
        LibLog.d(TAG,"processCache-->json="+json);
        if(cache){
            CacheManager.save(key,json);
        }
    }

    @Override
    protected void onProcessSuccess(Object obj) {
        if(obj instanceof GankioBaseBean){
            GankioBaseBean gankBaseBean = (GankioBaseBean) obj;
            onLoadSuccess(gankBaseBean.results);
        }
    }

    @Override
    protected void onProcessError(Object obj) {
        if(obj instanceof GankioBaseBean){
            onLoadError(new LibException(LibException.CODE_SELF_DEFINE, LibResUtils.getString(R.string.error_gankio)));
        }
    }

    @Override
    protected void onProcessOther(Object obj) {
        onLoadError(new LibException(LibException.CODE_SELF_DEFINE, LibResUtils.getString(R.string.error_no_match_bean)));
    }

}
