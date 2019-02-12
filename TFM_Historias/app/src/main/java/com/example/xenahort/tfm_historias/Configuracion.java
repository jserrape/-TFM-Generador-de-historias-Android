/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 12/02/19 2:52
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/02/19 16:07
 *
 */

package com.example.xenahort.tfm_historias;

import android.content.SharedPreferences;

import com.example.xenahort.tfm_historias.activities.MapsActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MapStyleOptions;

public class Configuracion {

    SharedPreferences preferencias;

    public Configuracion(SharedPreferences pref){
        this.preferencias=pref;
    }

    public void ponerEstilo(GoogleMap mMap, MapsActivity mapClass) {
        switch (this.preferencias.getString("estilo","silver")) {
            case "aubergine":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mapClass, R.raw.aubergine));
                break;
            case "dark":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mapClass, R.raw.dark));
                break;
            case "night":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mapClass, R.raw.night));
                break;
            case "retro":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mapClass, R.raw.retro));
                break;
            case "silver":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mapClass, R.raw.silver));
                break;
            case "standard":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mapClass, R.raw.standard));
                break;
        }
    }
}
