package com.androidatc.materialdesign.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.androidatc.materialdesign.MyApplication;

/**
 * Created by jorgecasariego on 1/9/15.
 */
public class VolleySingleton {
    private static VolleySingleton instance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> cache = new LruCache<>((int)(Runtime.getRuntime().maxMemory()/1024)/8);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(){
        if(instance == null){
            instance = new VolleySingleton();
        }

        return instance;
    }


    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
