package panhui.cn.bwf.itmmall.confing;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 继承Application
 * Created by Administrator on 2016/6/14.
 */
public class MyApplication extends Application {
    private static RequestQueue queue;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static RequestQueue getQueue() {
        return queue;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        queue = Volley.newRequestQueue(getApplicationContext());
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).build();

        ImageLoaderConfiguration conging = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheSize(50 * 1024 * 1024)
                .defaultDisplayImageOptions(displayImageOptions)
                .diskCacheSize(200 * 1024 * 1024)
                .diskCacheFileCount(150)//图片缓存数量
                .writeDebugLogs().build();

        ImageLoader.getInstance().init(conging);
        context = getApplicationContext();
    }
}
