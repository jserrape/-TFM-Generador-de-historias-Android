/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 12/02/19 2:52
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/02/19 1:42
 *
 */

package com.example.xenahort.tfm_historias;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Historia implements Serializable {
    private String Codigo;
    private String Descripcion;
    private String Nombre;
    private int zoom_inicial;
    private String imagenTitulo;
    private String longInicial;
    private String latInicial;
    private List<Mision> misiones;

    public Historia(String cod, String des, String nom,String zi,String it,String loi,String lai) {
        this.Codigo = cod;
        this.Descripcion = des;
        this.Nombre = nom;
        this.misiones = new ArrayList<>();
        this.zoom_inicial=Integer.parseInt(zi);
        this.imagenTitulo=it;
        this.longInicial=loi;
        this.latInicial=lai;
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

    public List<Mision> getMisiones() {
        return misiones;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getZoom_inicial() {
        return zoom_inicial;
    }

    public String getImagenTitulo() {
        return imagenTitulo;
    }

    public String getLongInicial() {
        return longInicial;
    }

    public String getLatInicial() {
        return latInicial;
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
