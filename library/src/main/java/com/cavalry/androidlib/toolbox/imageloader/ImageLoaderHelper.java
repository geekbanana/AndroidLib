package com.cavalry.androidlib.toolbox.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * 只需替换{@link com.cavalry.androidlib.toolbox.imageloader.LibImageLoader}的实现类即可替换图片加载框架
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class ImageLoaderHelper {

    private static ImageLoaderHelper mHelper;
    private static LibImageLoader mLoader;

    private ImageLoaderHelper(){}

    public static ImageLoaderHelper getInstance(){
        if(mHelper==null){
            synchronized (ImageLoaderHelper.class){
                if(mHelper==null){
                    mHelper = new ImageLoaderHelper();
                    mLoader = new GlideImageLoaderImpl();//替换这里的实现类,即可更换图片加载框架
                }
            }
        }
        return mHelper;
    }

    public void loadImage(Context context, String url, ImageView imageView, DisplayImageOptions options) {
        mLoader.loadImage(context,url,imageView,options);
    }

    public void loadImage(Activity activity, String url, ImageView imageView, DisplayImageOptions options) {
        mLoader.loadImage(activity,url,imageView,options);
    }

    public void loadImage(Fragment fragment, String url, ImageView imageView, DisplayImageOptions options) {
        mLoader.loadImage(fragment,url,imageView,options);
    }


}
