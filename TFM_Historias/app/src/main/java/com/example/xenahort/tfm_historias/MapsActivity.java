package com.example.xenahort.tfm_historias;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        solicitarAccesoUbicacion();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        configuracionInicialOnReady(mMap);


    }

    private void configuracionInicialOnReady(GoogleMap mMap) {
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
        LatLng center = new LatLng(37.1708, -3.607212);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        crearHistoria();
        historia.colocarMarcas(mMap);
    }

    private void solicitarAccesoUbicacion() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), VariablesMap.FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), VariablesMap.COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                VariablesMap.mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(this, permissions, VariablesMap.LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, VariablesMap.LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void crearHistoria(){
        InputStream inputStream = getResources().openRawResource(R.raw.fichero);
        String txt="";
        try {
            byte[] buffer = new byte[inputStream.available()];
            while (inputStream.read(buffer) != -1){
                txt = new String(buffer);
            }
            JSONObject object = new JSONObject(txt);
            this.historia=new Historia(object.getString("Codigo"),object.getString("Descripcion"),object.getString("Nombre historia"));
            JSONArray json_array = object.optJSONArray("Misiones");
            for (int i = 0; i < json_array.length(); i++) {
                historia.nuevaMision(json_array.getJSONObject(i).toString());
            }
            Log.d("tostringhistoria", this.historia.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
