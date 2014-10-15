/*
 * Module : PatternViews
 *
 * XMLParser
 *
 * Modified by @Asha on 15/10/14 06:15 PM
 *
 * Copyright (c) 2014  All rights reserved
 */


package com.switchmedia.parser;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.switchmedia.model.FileData;
import com.switchmedia.utils.Utils;

/**
 * The Class XMLParser used to parse the xml file
 */
public class XMLParser {

    private List<String> mCircleColors = new ArrayList<String>();
    private List<String> mSquareUrls = new ArrayList<String>();

    private XmlPullParserFactory xmlFactoryObject;
     public volatile boolean parsingComplete = true;

    public XMLParser() {

    }

    public void parseColorsAndStore(XmlPullParser myParser) {
        int event;
        String text = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals(Utils.KEY_HEX_CODE)) {
                            mCircleColors.add(text);
                        }
                        break;
                }
                event = myParser.next();

            }
            parsingComplete = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseURLsAndStore(XmlPullParser myParser) {
        int event;
        String text = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals(Utils.KEY_IMAGE_URL)) {
                            mSquareUrls.add(text);
                        }
                        break;
                }
                event = myParser.next();

            }
            parsingComplete = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fetchXML(final boolean isDownloadColorXml) {
        String mURL;

        try {
            if (isDownloadColorXml) {
                mURL = Utils.CIRCLES_URL;
            } else {
                mURL = Utils.SQUARES_URL;
            }
            URL url = new URL(mURL);
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream stream = conn.getInputStream();
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                    , false);
            parser.setInput(stream, null);
            if (isDownloadColorXml) {
                parseColorsAndStore(parser);
            } else {
                parseURLsAndStore(parser);
            }
            FileData.setCircleColors(mCircleColors);
            FileData.setSquareImageURLs(mSquareUrls);
            stream.close();
        } catch (Exception e) {
            Utils.dismissProgressBar();
            e.printStackTrace();
        }
    }
}