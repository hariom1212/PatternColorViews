/*
 * Module : PatternViews
 *
 * CircleView
 *
 * Modified by @Asha on 15/10/14 04:30 PM
 *
 * Copyright (c) 2014  All rights reserved
 */


package com.switchmedia.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.switchmedia.utils.Utils;

public class CircleView extends View {

    private Paint paint;
    private int circleColor;
    GestureDetector gestureDetector;
    boolean canImageMove = false;
    LinearLayout.LayoutParams layoutParams;
    private int _xDelta;
    private int _yDelta;

    int x;
    int y;


    public CircleView(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new GestureListener());

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(circleColor);

        float wf = getWidth();
        float hf = getHeight();
        final float cy = hf/2;
        wf -= getPaddingLeft() + getPaddingRight();
        hf -= getPaddingTop() + getPaddingBottom();
        float rad = (wf < hf) ? wf/2 : hf/2;

        if (canImageMove) {
            canvas.drawCircle(x, cy, rad, paint);
        } else {
            canvas.drawCircle(50, 50, rad, paint);

        }

    }

    public void setCircleColor(int newCircleColor) {
        circleColor = newCircleColor;
        invalidate();
        requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                canImageMove = true;
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                //canImageMove = true;
                // invalidate current position as we are moving...
                if(canImageMove) {
                    x = (int) event.getX();
                    y = (int) event.getY();
                    invalidate();
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                canImageMove = false;
                break;
        }
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            circleColor = Utils.generateRandomColors();
            invalidate();
            requestLayout();
            x = (int) e.getX();
            return true;
        }


    }


}