package com.cavalry.androidlib.toolbox.utils;

import android.support.annotation.Nullable;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class LibUtils {
    /**
     * 检查某个对象是否为空
     *
     * @param reference
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
