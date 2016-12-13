package com.cavalry.androidlib.toolbox.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public interface LibImageLoader {

    void loadImage(Context context, String url, ImageView imageView, DisplayImageOptions options);

    void loadImage(Activity activity, String url, ImageView imageView, DisplayImageOptions options);

    void loadImage(Fragment fragment, String url, ImageView imageView, DisplayImageOptions options);

}
