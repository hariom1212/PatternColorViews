/*
 * Module : PatternViews
 *
 * FileData
 *
 * Modified by @Asha on 15/10/14 06:50 PM
 *
 * Copyright (c) 2014  All rights reserved
 */

package com.switchmedia.model;


import java.util.ArrayList;
import java.util.List;


public final class FileData {

    private  static List<String> mSquareImageURLs = new ArrayList<String>();
    private static List<String> mCircleColors = new ArrayList<String>();


    public static List<String> getSquareImageURLs() {
        return mSquareImageURLs;
    }

    public static void setSquareImageURLs(List<String> squareImageURLs) {
        if (squareImageURLs != null) {
            mSquareImageURLs = squareImageURLs;
        }
    }

    public  static List<String> getCicleColors() {
        return mCircleColors;
    }

    public static void setCircleColors(List<String> circleColors) {
        if (circleColors != null) {
            mCircleColors = circleColors;
        }
    }

}
