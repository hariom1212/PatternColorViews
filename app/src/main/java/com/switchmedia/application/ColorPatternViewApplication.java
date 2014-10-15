/*
 * Module : PatternViews
 *
 * PatternViewApplication
 *
 * Modified by @Asha on 15/10/14 04:14 PM
 *
 * Copyright (c) 2014  All rights reserved
 */


package com.switchmedia.application;

import android.app.Application;
import android.util.Log;

import com.switchmedia.utils.ImageLoader;

/**
 * The Application class
 */
public class ColorPatternViewApplication extends Application {

    public static final String TAG = ColorPatternViewApplication.class.getSimpleName();

    private ImageLoader mImageLoader;

    private static ColorPatternViewApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ColorPatternViewApplication getInstance() {

        return mInstance;
    }

    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this);
        }
        return this.mImageLoader;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(TAG, "TRIMMING MEMORY");
        if(mImageLoader != null){
            mImageLoader.clearCache();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mImageLoader != null){
            mImageLoader.clearCache();
        }
    }


}