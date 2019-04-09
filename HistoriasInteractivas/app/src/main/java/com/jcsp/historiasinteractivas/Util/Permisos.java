/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 9/04/19 13:52
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 9/04/19 13:52
 *
 */

package com.jcsp.historiasinteractivas.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Permisos {

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    public void soliciarPermisoCamara(Context contexto) {
        if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Explicamos porque necesitamos el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) contexto, Manifest.permission.CAMERA)) {
            } else {
                // El usuario no necesitas explicación, puedes solicitar el permiso:
                ActivityCompat.requestPermissions((Activity) contexto, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }

    public void soliciarPermisoLocalizacion(Context contexto) {
        if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Explicamos porque necesitamos el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) contexto, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                // El usuario no necesitas explicación, puedes solicitar el permiso:
                ActivityCompat.requestPermissions((Activity) contexto, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    }
}
