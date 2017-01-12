package com.cavalry.androidlib.toolbox.imageloader;

import android.graphics.drawable.Drawable;

/**
 * <p>图片显示的一些可选功能</p>
 * <p>目前仅提供了Glide相关属性的选项.其他工具选项待完善</p>
 * @author Cavalry Lin
 * @since 1.0.0
 */
public class DisplayImageOptions {
    public Drawable placeHolder;
    public Drawable error;
    public boolean centerCrop;
    public boolean fitCenter;
    public boolean asBitmap;
    public boolean asGif;
    public DiskCacheStrategy diskCacheStrategy;
    public MemoryCacheStrategy memoryCacheStrategy;

    private DisplayImageOptions(DisplayImageOptions.Builder builder) {
        placeHolder = builder.placeHolder;
        error = builder.error;
        centerCrop = builder.centerCrop;
        fitCenter = builder.fitCenter;
        asBitmap = builder.asBitmap;
        asGif = builder.asGif;
        diskCacheStrategy = builder.diskCacheStrategy;
        memoryCacheStrategy = builder.memoryCacheStrategy;
    }

    public static class Builder {
        private Drawable placeHolder;
        private Drawable error;
        private boolean centerCrop;
        private boolean fitCenter;
        private boolean asBitmap;
        private boolean asGif;
        private DiskCacheStrategy diskCacheStrategy;
        private MemoryCacheStrategy memoryCacheStrategy;

        public Builder placeHolder(Drawable placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder error(Drawable error) {
            this.error = error;
            return this;
        }

        public Builder asBitmap(boolean asBitmap) {
            this.asBitmap = asBitmap;
            return this;
        }

        public Builder asGif(boolean asGif) {
            this.asGif = asGif;
            return this;
        }

        public Builder centerCrop(boolean centerCrop) {
            this.centerCrop = centerCrop;
            return this;
        }

        public Builder fitCenter(boolean fitCenter) {
            this.fitCenter = fitCenter;
            return this;
        }

        public Builder diskCacheStrategy(DiskCacheStrategy diskCacheStrategy){
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Builder memoryCacheStrategy(MemoryCacheStrategy memoryCacheStrategy){
            this.memoryCacheStrategy = memoryCacheStrategy;
            return this;
        }

        public DisplayImageOptions build() {
            return new DisplayImageOptions(this);
        }
    }

    public enum DiskCacheStrategy {
        NONE,//不缓存
        SOURCE,//只缓存原图
        RESULT,//只缓存转换过后的图(如缩略图)
        CACHE//全缓存
    }

    public enum MemoryCacheStrategy{
        SKIP//不缓存
    }
}
