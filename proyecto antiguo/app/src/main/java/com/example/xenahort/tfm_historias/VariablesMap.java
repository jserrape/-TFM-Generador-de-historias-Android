/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 12/02/19 2:53
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 10/01/19 20:07
 *
 */

package com.example.xenahort.tfm_historias;

import android.Manifest;

public class VariablesMap {

    public static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    public static Boolean mLocationPermissionsGranted = false;
}
