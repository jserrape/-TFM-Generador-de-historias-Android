/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 12/02/19 2:51
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/02/19 1:51
 *
 */

package com.example.xenahort.tfm_historias.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xenahort.tfm_historias.Configuracion;
import com.example.xenahort.tfm_historias.Historia;
import com.example.xenahort.tfm_historias.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Configuracion conf;

    private Historia historia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        conf= new Configuracion(getSharedPreferences("Preferencias",Context.MODE_PRIVATE));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        configuracionInicialOnReady(mMap);

        cargarHistoria();
    }

    private void configuracionInicialOnReady(GoogleMap mMap) {
        this.historia= (Historia) getIntent().getSerializableExtra("Historia");

        //Pongo un nuevo estilo al mapa
        conf.ponerEstilo(mMap, this);

        //Añado botones de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Pongo mi ubicacion
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        //Pongo la camara en el sitio de prueba
        LatLng center = new LatLng(Double.parseDouble(historia.getLatInicial()), Double.parseDouble(historia.getLongInicial()));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(historia.getZoom_inicial()).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void cargarHistoria(){
        LatLng coor;
        int drawableResourceId;
        for (int i = 0; i < historia.getMisiones().size(); i++) {
            Log.d("MARCADOR", i+":"+historia.getMisiones().get(i).getNombre());
            coor = new LatLng(Double.parseDouble(historia.getMisiones().get(i).getLatitud()), Double.parseDouble(historia.getMisiones().get(i).getLongitud()));

            drawableResourceId = this.getResources().getIdentifier(historia.getMisiones().get(i).getIcono(), "drawable", this.getPackageName());
            Bitmap b = ((BitmapDrawable) getResources().getDrawable(drawableResourceId)).getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);

            mMap.addMarker(new MarkerOptions()
                    .position(coor)
                    .title(historia.getMisiones().get(i).getNombre())
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
            );
        }
    }

}
