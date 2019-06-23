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
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.jcsp.historiasinteractivas.Dialogos.EscaneoQRDialogo;
import com.jcsp.historiasinteractivas.Dialogos.FeliciacionUbicacionDialogo;
import com.jcsp.historiasinteractivas.Dialogos.FinalHistoriaDialogo;
import com.jcsp.historiasinteractivas.Dialogos.PresentacionFinalMisionDialogo;
import com.jcsp.historiasinteractivas.Dialogos.PresentacionMisionDialogo;
import com.jcsp.historiasinteractivas.Dialogos.PruebaQRDialogo;
import com.jcsp.historiasinteractivas.Dialogos.QuizDialogo;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Objetos_gestion.Historia;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.BitmapFactory.decodeByteArray;
import static com.facebook.FacebookSdk.getApplicationContext;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private NavigationDrawerActivity nd;
    private Historia historia;

    private int nMision;
    private List<Marker> marks;

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
        nMision = 0;

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

    private void configuracionPreferenciasMapa() {
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

    private void cargarConfiguracionCamara() {
        //Coloco la camara en su sitio y hago zoom
        LatLng center = new LatLng(historia.getLatitud_historia(), historia.getLongitud_historia());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(historia.getZoom_historia()).build();
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void insertarMarcas() {
        marks = new ArrayList<>();
        boolean precedentesCompleados;
        String parts[];
        for (int i = 0; i < historia.getMisiones().size(); i++) {
            precedentesCompleados = true;
            parts = historia.getMisiones().get(i).getPrecedentes().replaceAll(" ", "").split(",");
            //Compruebo si se han completado las misiones preedentes
            for (int j = 0; j < parts.length; j++) {
                if (!parts[j].equals("")) {
                    if (historia.getMisiones().get(Integer.parseInt(parts[j]) - 1).getCompletado().equals("False")) {
                        precedentesCompleados = false;
                        break;
                    }
                }
            }

            //Si la he completado, miro las que cancelo
            if (!historia.getMisiones().get(i).getCompletado().equals("False")) {
                parts = historia.getMisiones().get(i).getMisiones_canceladas().replaceAll(" ", "").split(",");
                for (int j = 0; j < parts.length; j++) {
                    if (!parts[j].equals("")) {
                        historia.getMisiones().get(Integer.parseInt(parts[j]) - 1).setAnulada(true);
                    }
                }
            }

            if (historia.getMisiones().get(i).getCompletado().equals("False") && precedentesCompleados && !historia.getMisiones().get(i).isAnulada()) {
                byte[] imageAsBytes = Base64.decode(historia.getMisiones().get(i).getIcono_mision().getBytes(), Base64.DEFAULT);

                int height = 110;
                int width = 110;

                Bitmap smallMarker = Bitmap.createScaledBitmap(decodeByteArray(imageAsBytes, 0, imageAsBytes.length), width, height, false);

                MarkerOptions markOp = new MarkerOptions()
                        .position(new LatLng(historia.getMisiones().get(i).getLatitud_mision(), historia.getMisiones().get(i).getLongitud_mision()))
                        .title(historia.getMisiones().get(i).getNombre_mision()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                Marker marker = mMap.addMarker(markOp);
                marker.setTag(i);
                marks.add(marker);
            }
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                nMision = (int) (marker.getTag());
                if (historia.getMisiones().get(nMision).getImagen_final() == null) {
                    GetPostService mAPIService = ApiUtils.getAPIService();
                    mAPIService.solicitud_mision(historia.getMisiones().get(nMision).getId()).enqueue(new Callback<Mision>() {
                        @Override
                        public void onResponse(Call<Mision> call, Response<Mision> response) {
                            historia.getMisiones().get(nMision).setTipo_localizacion(response.body().getTipo_localizacion());
                            historia.getMisiones().get(nMision).setCodigo_localizacion(response.body().getCodigo_localizacion());
                            historia.getMisiones().get(nMision).setTipo_prueba(response.body().getTipo_prueba());
                            historia.getMisiones().get(nMision).setCodigo_prueba(response.body().getCodigo_prueba());
                            historia.getMisiones().get(nMision).setDescripcion_inicial(response.body().getDescripcion_inicial());
                            historia.getMisiones().get(nMision).setImagen_inicial(response.body().getImagen_inicial());
                            historia.getMisiones().get(nMision).setDescripcion_final(response.body().getDescripcion_final());
                            historia.getMisiones().get(nMision).setImagen_final(response.body().getImagen_final());

                            iniciarDialogo(1);
                        }

                        @Override
                        public void onFailure(Call<Mision> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    iniciarDialogo(1);
                }
                return false;
            }
        });
    }

    public boolean comprobarFinal() {
        return historia.getMisiones().get(nMision).isMision_final() != 0;
    }

    public void comprobarCancelaciones() {
        if (!historia.getMisiones().get(nMision).getMisiones_canceladas().equals("")) {
            String parts[] = historia.getMisiones().get(nMision).getPrecedentes().replaceAll(" ", "").split(",");
            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].equals("")) {
                    historia.getMisiones().get(Integer.parseInt(parts[i]) - 1).setAnulada(true);
                }
            }
        }
    }

    public void eliminarMarks() {
        while (!marks.isEmpty()){
            marks.get(0).remove();
            marks.remove(0);
        }
    }

    public void iniciarDialogo(int n) {
        switch (n) {
            case 1:
                new PresentacionMisionDialogo(getContext(), historia.getMisiones().get(nMision), this);
                break;
            case 2:
                new EscaneoQRDialogo(getContext(), historia.getMisiones().get(nMision), this);
                break;
            case 3:
                //Escaneo Beacon
                break;
            case 4:
                new FeliciacionUbicacionDialogo(getContext(), historia.getMisiones().get(nMision), this);
                break;
            case 5:
                //Escaneo QR
                new PruebaQRDialogo(getContext(), historia.getMisiones().get(nMision), this);
                break;
            case 6:
                //Hacer pregunta
                new QuizDialogo(getContext(), historia.getMisiones().get(nMision), this);
                break;
            case 7:
                //Vista final de mision
                new PresentacionFinalMisionDialogo(getContext(), historia.getMisiones().get(nMision), this);
                break;
            case 8:
                //Vista final de la historia
                new FinalHistoriaDialogo(getContext(),historia.getNombre_historia());
                break;
        }
    }
}