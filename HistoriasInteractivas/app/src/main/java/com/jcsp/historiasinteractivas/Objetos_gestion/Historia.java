/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:14
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 4:45
 *
 */

package com.jcsp.historiasinteractivas.Objetos_gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Historia implements Serializable {

    private int id;
    private String nombre_historia;
    private String idioma_historia;
    private String imagen_historia;
    private Double latitud_historia;
    private Double longitud_historia;
    private int zoom;
    private String descripcion_historia;
    private List<Mision> misiones = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getDescripcion_historia() {
        return descripcion_historia;
    }

    public void setDescripcion_historia(String descripcion_historia) {
        this.descripcion_historia = descripcion_historia;
    }

    public String getIdioma_historia() {
        return idioma_historia;
    }

    public void setIdioma_historia(String idioma_historia) {
        this.idioma_historia = idioma_historia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_historia() {
        return nombre_historia;
    }

    public void setNombre_historia(String nombre_historia) {
        this.nombre_historia = nombre_historia;
    }

    public String getImagen_historia() {
        return imagen_historia;
    }

    public void setImagen_historia(String imagen_historia) {
        this.imagen_historia = imagen_historia;
    }

    public Double getLatitud_historia() {
        return latitud_historia;
    }

    public void setLatitud_historia(Double latitud_historia) {
        this.latitud_historia = latitud_historia;
    }

    public Double getLongitud_historia() {
        return longitud_historia;
    }

    public void setLongitud_historia(Double longitud_historia) {
        this.longitud_historia = longitud_historia;
    }

    public int getZoom_historia() {
        return zoom;
    }

    public void setZoom_historia(int zoom_historia) {
        this.zoom = zoom_historia;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public List<Mision> getMisiones() {
        return misiones;
    }

    public void setList(List<Mision> list) {
        this.misiones = list;
    }

    @Override
    public String toString() {
        return "Historia{" +
                "id=" + id +
                ", nombre_historia='" + nombre_historia + '\'' +
                ", idioma_historia='" + idioma_historia + '\'' +
                ", latitud_historia=" + latitud_historia +
                ", longitud_historia=" + longitud_historia +
                ", zoom='" + zoom + '\'' +
                ", descripcion_historia='" + descripcion_historia + '\'' +
                ", misiones=" + misiones +
                '}';
    }
}