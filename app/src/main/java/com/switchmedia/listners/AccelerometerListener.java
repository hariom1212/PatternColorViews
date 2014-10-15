/*
 * Module : PatternViews
 *
 * AccelerometerListener
 *
 * Modified by @Asha on 15/10/14 08:50 PM
 *
 * Copyright (c) 2014  All rights reserved
 */

package com.switchmedia.listners;

public interface AccelerometerListener {

    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);

}