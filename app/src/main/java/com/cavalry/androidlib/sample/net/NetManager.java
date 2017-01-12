package com.cavalry.androidlib.sample.net;

import com.cavalry.androidlib.net.DefaultNetManager;

import okhttp3.OkHttpClient;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class NetManager {

    private static OkHttpClient mClient;

    public static OkHttpClient getOkHttpClient() {
        if(mClient == null){
            synchronized (DefaultNetManager.class){
                if(mClient ==null){
                    mClient = new OkHttpClient();
                }
            }
        }
        return mClient;
    }

}
