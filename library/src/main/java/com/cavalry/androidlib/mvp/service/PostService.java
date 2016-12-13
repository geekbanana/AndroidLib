package com.cavalry.androidlib.mvp.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public interface PostService {
    /**
     *
     * @param url 完整的url地址
     * @param params　参数(如果没有参数,需传递一个空的Map)
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String, String> params);
}
