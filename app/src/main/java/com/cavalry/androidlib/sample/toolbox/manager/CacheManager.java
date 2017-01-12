package com.cavalry.androidlib.sample.toolbox.manager;

import com.cavalry.androidlib.sample.BuildConfig;
import com.cavalry.androidlib.sample.SampleApplication;
import com.cavalry.androidlib.toolbox.cache.disk.DiskCache;
import com.cavalry.androidlib.toolbox.cache.disk.impl.DiskLruCache;
import com.cavalry.androidlib.toolbox.cache.memory.MemoryCache;
import com.cavalry.androidlib.toolbox.cache.memory.impl.StringLruCache;
import com.cavalry.androidlib.toolbox.utils.LibEncodeUtils;
import com.cavalry.androidlib.toolbox.utils.LibLog;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例获取MemoryCache和DiskCache
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class CacheManager {
    private final static String TAG = "CacheManager";
    private final static long MAX_MEMORY_CACHE_SIZE = 8*1024*1024;//8M
    private static File directory = SampleApplication.getContext().getCacheDir();
    private final static long MAX_DISK_CACHE_SIZE = 32*1024*1024;//32M

    private static MemoryCache<String,String> memoryCache = new StringLruCache(MAX_MEMORY_CACHE_SIZE);
    private static DiskCache<String,String> diskCache = DiskLruCache.open(directory,MAX_DISK_CACHE_SIZE, BuildConfig.VERSION_CODE);;

    //TODO 具体线程池类型待定
    final static ThreadPoolExecutor executorService =
            new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private CacheManager(){}


    public static void save(String key, final String value){
        if(key==null){
            LibLog.e(TAG,"save-->key = null");
            return;
        }

        if(value==null){
            LibLog.e(TAG,"save-->value = null");
            return;
        }

        final String md5Key = LibEncodeUtils.md5(key);
        memoryCache.put(md5Key,value);
        LibLog.d(TAG,"存入Memeory成功");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                boolean save = diskCache.save(md5Key, value);
                if(save){
                    LibLog.d(TAG,"存入Disk成功");
                }else{
                    LibLog.d(TAG,"存入Disk失败");
                }
            }
        });
    }


    public static String  get(String key){
        if(key==null){
            LibLog.e(TAG,"get-->key = null");
            return null;
        }

        final String md5Key = LibEncodeUtils.md5(key);
        String value = memoryCache.get(md5Key);

        LibLog.d(TAG,"尝试从Memory中获取: value="+value);

        if(value==null){
            Future<String> future = executorService.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    return diskCache.get(md5Key);
                }
            });
            try {
                value = future.get();
                LibLog.d(TAG,"尝试从Disk中获取: value="+value);
                if(value!=null){
                    LibLog.d(TAG,"从Disk中获取");
                    memoryCache.put(md5Key,value);
                }
            }  catch (Exception e) {
                e.printStackTrace();
                value = null;
            }
        }else{
            LibLog.d(TAG,"从Memory中获取");
        }

        if(value==null){
            LibLog.d(TAG,"无法从缓存获取");
        }

        return value;
    }

    public static void close(){
        if(diskCache!=null){
            diskCache.close();
        }
    }
}
