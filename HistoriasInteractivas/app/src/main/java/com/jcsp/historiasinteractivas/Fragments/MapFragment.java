/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:43
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:43
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jcsp.historiasinteractivas.Actividades.NavigationDrawerActivity;
import com.jcsp.historiasinteractivas.Dialogos.PresentacionMisionDialogo;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.Historia;

import static android.graphics.BitmapFactory.decodeByteArray;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private NavigationDrawerActivity nd;
    private Historia historia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        this.historia = ((NavigationDrawerActivity) getActivity()).getHistoria();
        getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        configuracionPreferenciasMapa();
        cargarConfiguracionCamara();
        insertarMarcas();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void configuracionPreferenciasMapa(){
        //Estilo
        SharedPreferences prefs = this.getActivity().getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        switch (prefs.getString("estilo", "0")) {
            case "0":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.standard));
                break;
            case "1":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.dark));
                break;
            case "2":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.silver));
                break;
            case "3":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.aubergine));
                break;
            case "4":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.night));
                break;
            case "5":
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.retro));
                break;
            case "6":
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
        }

        //Centrar
        mMap.getUiSettings().setMyLocationButtonEnabled(Boolean.parseBoolean(prefs.getString("centrar", "true")));

        //Zoom
        mMap.getUiSettings().setZoomControlsEnabled(Boolean.parseBoolean(prefs.getString("zoom", "true")));

        //Brujula
        mMap.getUiSettings().setCompassEnabled(true);
    }

    private void cargarConfiguracionCamara(){
        //Coloco la camara en su sitio y hago zoom
        LatLng center = new LatLng(historia.getLatitud_historia(), historia.getLongitud_historia());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(historia.getZoom_historia()).build();
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void insertarMarcas(){
        for(int i=0;i < historia.getMisiones().size();i++){
            byte[] imageAsBytes = Base64.decode(historia.getMisiones().get(i).getIcono_mision().getBytes(), Base64.DEFAULT);

            int height = 110;
            int width = 110;
            Bitmap smallMarker = Bitmap.createScaledBitmap(decodeByteArray(imageAsBytes, 0, imageAsBytes.length), width, height, false);
            Marker marker =mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(historia.getMisiones().get(i).getLatitud_mision(), historia.getMisiones().get(i).getLongitud_mision()))
                    .title(historia.getMisiones().get(i).getNombre_mision()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
            );

            marker.setTag(i);
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int position = (int)(marker.getTag());
                Toast.makeText(getContext(), "Hola "+position, Toast.LENGTH_SHORT).show();

                new PresentacionMisionDialogo(getContext(),historia.getMisiones().get((int)(marker.getTag())));

                return false;
            }
        });
    }
}