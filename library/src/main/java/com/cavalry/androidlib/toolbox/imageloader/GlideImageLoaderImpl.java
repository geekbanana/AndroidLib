package com.cavalry.androidlib.toolbox.imageloader;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class GlideImageLoaderImpl implements LibImageLoader {

    @Override
    public void loadImage(Context context, String url, ImageView imageView, DisplayImageOptions options) {
        DrawableTypeRequest<String> request = Glide.with(context)
                .load(url);
        processOptions(options,request);
        request.into(imageView);
    }

    @Override
    public void loadImage(Activity activity, String url, ImageView imageView, DisplayImageOptions options) {
        DrawableTypeRequest<String> request = Glide.with(activity)
                .load(url);
        processOptions(options,request);
        request.into(imageView);
    }

    @Override
    public void loadImage(Fragment fragment, String url, ImageView imageView, DisplayImageOptions options) {
        DrawableTypeRequest<String> request = Glide.with(fragment)
                .load(url);
        processOptions(options,request);
        request.into(imageView);

    }

    private void processOptions(DisplayImageOptions options, DrawableTypeRequest<String> request) {
        if(options!=null){
            if(options.asBitmap){
                request.asBitmap();
            }else if(options.asGif){
                request.asGif();
            }

            if(options.placeHolder!=null){
                request.placeholder(options.placeHolder);
            }
            if(options.error!=null){
                request.error(options.error);
            }

            if(options.centerCrop){
                request.centerCrop();
            }else if(options.fitCenter){
                request.fitCenter();
            }

            if(options.diskCacheStrategy !=null && options.diskCacheStrategy != DisplayImageOptions.DiskCacheStrategy.NONE){
                if(options.diskCacheStrategy== DisplayImageOptions.DiskCacheStrategy.CACHE){
                    request.diskCacheStrategy(DiskCacheStrategy.ALL);
                }else if(options.diskCacheStrategy== DisplayImageOptions.DiskCacheStrategy.SOURCE){
                    request.diskCacheStrategy(DiskCacheStrategy.SOURCE);
                }else if(options.diskCacheStrategy== DisplayImageOptions.DiskCacheStrategy.RESULT){
                    request.diskCacheStrategy(DiskCacheStrategy.RESULT);
                }
            }

            if(options.memoryCacheStrategy!=null){
                if(options.memoryCacheStrategy== DisplayImageOptions.MemoryCacheStrategy.SKIP){
                    request.skipMemoryCache(true);
                }
            }
        }
    }


}
