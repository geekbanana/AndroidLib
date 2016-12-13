package com.cavalry.androidlib.toolbox.managers;

import com.cavalry.androidlib.toolbox.managers.gsonadapter.DoubleDefault0Adapter;
import com.cavalry.androidlib.toolbox.managers.gsonadapter.IntegerDefault0Adapter;
import com.cavalry.androidlib.toolbox.managers.gsonadapter.LongDefault0Adapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rx.internal.util.ObjectPool;

/**
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class GsonManager {
    private GsonManager(){}

    private static GsonPool gsonPool = new GsonPool();

    public static Gson getGson(){
       return gsonPool.borrowObject();
    }

    public static void recycleGson(Gson gson){
        gsonPool.returnObject(gson);
    }

    static class GsonPool extends ObjectPool<Gson> {

        @Override
        protected Gson createObject() {
            return new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
                    .create();
        }
    }

}
