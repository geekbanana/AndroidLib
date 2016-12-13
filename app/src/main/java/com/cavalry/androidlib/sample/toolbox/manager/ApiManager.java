package com.cavalry.androidlib.sample.toolbox.manager;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */
public class ApiManager {
    private static int mType;
    private final static int TYPE_TEST = 1;
    private final static int TYPE_PRODUCTION = 2;


    public static String mGankioHost;
    public final static String GANKIO_HOST_TEST = "http://gank.io/api";
    public final static String GANKIO_HOST_PRODUCTION = "http://gank.io/api";

    static {
        mType = TYPE_PRODUCTION;
        switch (mType){
            case TYPE_TEST:
                mGankioHost = GANKIO_HOST_TEST;
                break;
            case TYPE_PRODUCTION:
                mGankioHost = GANKIO_HOST_PRODUCTION;
                break;
        }
    }

    //分类数据
    public final static String GANKIO_BEAUTY = mGankioHost + "/data/福利";

}
