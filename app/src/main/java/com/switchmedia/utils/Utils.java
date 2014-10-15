/*
 * Module : PatternViews
 *
 * Utils
 *
 * Modified by @Asha on 15/10/14 04:35 PM
 *
 * Copyright (c) 2014  All rights reserved
 */

package com.switchmedia.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import com.switchmedia.R;
import com.switchmedia.application.ColorPatternViewApplication;
import com.switchmedia.model.FileData;


public final class Utils {

    // All static variables
    public static final String SQUARES_URL = "http://www.colourlovers.com/api/patterns/random";
    public static final String CIRCLES_URL = "http://www.colourlovers.com/api/colors/random";

    public static final String KEY_HEX_CODE = "hex"; //  node
    public static final String KEY_IMAGE_URL = "imageUrl"; //  node

    private static ProgressDialog sProgressDialog = null;


    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
            //TODO:: Handle as per the requirement
        }
    }

    /**
     * Show progress bar.
     */
    public static void showProgressBar(Context context) {
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setMessage(context.getResources()
                .getString(R.string.wait));
        sProgressDialog.setCanceledOnTouchOutside(false);
        sProgressDialog.setCancelable(false);
        sProgressDialog.show();

    }

    public static int generateRandomColors(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    /**
     * Dismiss Progress Bar
     */
    public static void dismissProgressBar(){
        if(sProgressDialog != null){
            sProgressDialog.dismiss();        }
    }

    /**
     * Function for check the network connectivity.
     *
     * @param context the context
     * @return true if network Available otherwise false
     */
    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * This Method is used to set the Pattern on Square shape based on Network Connection
     *
     * @param imgView Imageview
     */
    public static void setPattern(ImageView imgView) {
        List<String> squareImageUrls = FileData.getSquareImageURLs();
        Random r = new Random();
        if (squareImageUrls != null && squareImageUrls.size() > 0) {
            String URL = squareImageUrls.get(r.nextInt(squareImageUrls.size()));
            ColorPatternViewApplication.getInstance().getImageLoader().DisplayImage(URL, imgView);
        } else {
            imgView.setBackgroundColor(Utils.generateRandomColors());
        }
    }


}
