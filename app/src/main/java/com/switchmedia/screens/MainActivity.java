/*
 * Module : PatternViews
 *
 * MainActivity
 *
 * Modified by @Asha on 15/10/14 04:15 PM
 *
 * Copyright (c) 2014  All rights reserved
 */

package com.switchmedia.screens;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.switchmedia.R;
import com.switchmedia.application.ColorPatternViewApplication;
import com.switchmedia.asynctask.DownloadTask;
import com.switchmedia.customviews.CircleView;
import com.switchmedia.listners.AccelerometerListener;
import com.switchmedia.listners.TouchListener;
import com.switchmedia.model.AccelerometerManager;
import com.switchmedia.model.FileData;
import com.switchmedia.utils.Utils;

import java.util.List;
import java.util.Random;


/**
 * The Main Activity Class
 */
public class MainActivity extends Activity implements AccelerometerListener {

    private LinearLayout mLinearLayout;
    private LinearLayout mSubLinearLayout;
    //private ScrollView mScrollView;
    private int MIN = 60, MAX = 100;
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //Initialize async task to download and parse the xml files
        if (Utils.isNetworkAvailable(this)) {
            DownloadTask asyncTask = new DownloadTask(this);
            asyncTask.execute();
        }

    }

    /**
     * This Method is used to initialize all the ui component in the layout
     */
    private void initView() {
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mSubLinearLayout = (LinearLayout) findViewById(R.id.sublinearLayout);
       // mScrollView = (ScrollView) findViewById(R.id.scrollView1);
        //Add touch listener
        addTouchListenerToLayout();
    }

    /**
     * This Method is used to add the touch OnTouchListener to the LinearLayout
     */
    private void addTouchListenerToLayout() {
        mLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    addView();
                    i++;
                }
                return true;
            }
        });

    }

    /**
     * Add View to the Layout in Alternate way
     * Note : this can be changed based on Requirement
     */
    private void addView() {
        if (i % 2 == 0) {
            setCircleView();
        } else {
            setSquareView();
        }

    }


    /**
     * Sets the Custom Circle View
     */
    private void setCircleView() {
        int color;
        Random r = new Random();
        CircleView circle = new CircleView(MainActivity.this);
        circle.setMinimumWidth(100);
        circle.setMinimumHeight(100);
        List<String> circleColors = FileData.getCicleColors();
        if (circleColors != null && circleColors.size() > 0) {
            String ColorValue = circleColors.get(r.nextInt(circleColors.size()));
            color = Color.parseColor("#" + ColorValue);
        } else {
            color = Utils.generateRandomColors();
        }
        circle.setCircleColor(color);
         Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
         circle.setAnimation(animation);
        mSubLinearLayout.addView(circle);
    }

    /**
     * Sets the Square View
     */
    private void setSquareView() {
        ImageView imgView = new ImageView(MainActivity.this);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
        imgView.setAnimation(animation);

        setGestureListener(imgView);
        Utils.setPattern(imgView);
        mSubLinearLayout.addView(imgView, getLayoutParam(false));

    }

    /**
     * This Method is used to get the Layout Param for PatterView Linear Layout
     *
     * @param isCircle to differentiate between square and circle view
     * @return layoutParam
     */
    private LinearLayout.LayoutParams getLayoutParam(boolean isCircle) {

        int randomVal = MIN + (int) (Math.random() * ((MAX - MIN) + 1));

        float density = getResources().getDisplayMetrics().density;
        int finalDimens = (int) (randomVal * density);

        // SET THE MARGIN
        int dimensMargin = 2;
        float densityMargin = getResources().getDisplayMetrics().density;
        int finalDimensMargin = (int) (dimensMargin * densityMargin);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(finalDimens, finalDimens);
        param.setMargins(finalDimensMargin, finalDimensMargin, finalDimensMargin, finalDimensMargin);
        return param;
    }

    /**
     * The Gesture Listner for ImageView
     *
     * @param imgView ImageView
     */
    private void setGestureListener(final ImageView imgView) {
        imgView.setOnTouchListener(new TouchListener(imgView, MainActivity.this));

    }


    public void onAccelerationChanged(float x, float y, float z) {

    }

    public void onShake(float force) {
        if (mSubLinearLayout != null && mSubLinearLayout.getChildCount() > 0) {
            mSubLinearLayout.removeAllViews();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //Check device supported Accelerometer sensor or not
        if (AccelerometerManager.isSupported(this)) {

            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //Check device supported Accelerometer sensor or not
        if (AccelerometerManager.isListening()) {
            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ColorPatternViewApplication.getInstance().getImageLoader().clearCache();
        //Check device supported Accelerometer sensor or not
        if (AccelerometerManager.isListening()) {
            //Start Accelerometer Listening
            AccelerometerManager.stopListening();
        }

    }

}
