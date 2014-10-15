/*
 * Module : PatternViews
 *
 * TouchListener
 *
 * Modified by @Asha on 15/10/14 06:50 PM
 *
 * Copyright (c) 2014  All rights reserved
 */

package com.switchmedia.listners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.switchmedia.utils.Utils;

public class TouchListener implements View.OnTouchListener {

    private int _xDelta;
    private int _yDelta;
    private ImageView imgView;
    private Context mContext;

    public TouchListener(ImageView imageView, Context context) {
        imgView = imageView;
        mContext = context;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = 10;
                layoutParams.bottomMargin = 10;
                v.setLayoutParams(layoutParams);
                break;
        }
        return true;
    }

    GestureDetector mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Utils.setPattern(imgView);
            return super.onDoubleTap(e);
        }
    });

}


