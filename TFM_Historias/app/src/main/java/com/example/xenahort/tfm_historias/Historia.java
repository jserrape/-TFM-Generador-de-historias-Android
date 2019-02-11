package com.example.xenahort.tfm_historias;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Historia {
    private String Codigo;
    private String Descripcion;
    private String Nombre;
    private List<Mision> misiones;

    public Historia(String cod, String des, String nom) {
        this.Codigo = cod;
        this.Descripcion = des;
        this.Nombre = nom;
        this.misiones = new ArrayList<>();
    }

    public void nuevaMision(String mision) throws JSONException {
        JSONObject object = new JSONObject(mision);
        JSONObject objectaux;
        String cod_mision, nombre, pista, tipo, cod_tipo, icono, latitud, longitud, texto;

        cod_mision = object.getString("Codigo");
        nombre = object.getString("Nombre");
        pista = object.getString("Pista de audio");
        objectaux = new JSONObject(object.getString("Tipo"));
        tipo = objectaux.getString("Tipo");
        cod_tipo = objectaux.getString("Codigo");
        icono = object.getString("Icono");
        objectaux = new JSONObject(object.getString("Coordenadas"));
        latitud = objectaux.getString("Latitud");
        longitud = objectaux.getString("Longitud");
        texto = object.getString("Texto");
        //precedentes[]

        Mision m = new Mision(cod_mision, nombre, pista, tipo, cod_tipo, icono, latitud, longitud, texto);
        misiones.add(m);
        Log.d("mision", m.toString());
    }

    public void colocarMarcas(GoogleMap mMap) {
        LatLng coor;
        for(int i=0;i<misiones.size();i++){
            coor = new LatLng(Double.parseDouble(misiones.get(i).getLatitud()), Double.parseDouble(misiones.get(i).getLongitud()));
            mMap.addMarker(new MarkerOptions().position(coor).title(misiones.get(i).getNombre()));
        }
    }

    @Override
    public String toString() {
        return "Historia{" +
                "Codigo='" + Codigo + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}
