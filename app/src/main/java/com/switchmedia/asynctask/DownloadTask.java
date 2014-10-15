/*
 * Module : PatternViews
 *
 * DownloadTask
 *
 * Modified by @Asha on 15/10/14 06:35 PM
 *
 * Copyright (c) 2014  All rights reserved
 */

package com.switchmedia.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.switchmedia.parser.XMLParser;
import com.switchmedia.utils.Utils;


public class DownloadTask extends AsyncTask<String, Void, String> {

    private Context mContext;


    public DownloadTask(Context context) {

        mContext = context;
     }

    /*
    * (non-Javadoc)
    *
    * @see android.os.AsyncTask#onPreExecute()
    */
    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        Utils.showProgressBar(mContext);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected String doInBackground(final String... params) {
        downloadAndParseFile(true);
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(final String result) {
        super.onPostExecute(result);
        DownloadSquareURLs downloadURLs = new DownloadSquareURLs();
        downloadURLs.execute();
    }


    private class DownloadSquareURLs extends AsyncTask<String, Void, String> {

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#doInBackground(Params[])
         */
        @Override
        protected String doInBackground(final String... params) {
            downloadAndParseFile(false);
            return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(final String result) {
            super.onPostExecute(result);
            Utils.dismissProgressBar();
        }
    }

    /**
     *  This method is used to download and parse the xml file
     * @param isColorsFile to diffrentiate between square and color view
     */
    private void downloadAndParseFile(boolean isColorsFile) {

        XMLParser xmlParser = new XMLParser();
        xmlParser.fetchXML(isColorsFile);
       // while (xmlParser.parsingComplete) ;
    }


}
