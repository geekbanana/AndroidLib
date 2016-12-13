package com.cavalry.androidlib.sample.ui.fragment.gankio.base;

import com.cavalry.androidlib.sample.ui.fragment.base.BaseFragment;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public abstract class GankioBaseFragment extends BaseFragment {

    /**
     * 给url拼接参数
     * @param url
     * @param params
     * @return
     */
    protected String assembleUrl(String url, String ...params){
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if(params!=null && params.length>0){
            for(String param : params){
                sb.append("/")
                        .append(param);
            }
        }
        return sb.toString();
    }
}
