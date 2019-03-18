package com.jcsp.historias_tfm.Util;

import java.io.Serializable;

public class Historia implements Serializable {

    private int id;
    private String nombre_historia;
    private String idioma_historia;
    private String imagen_historia;
    private String latitud_historia;
    private String longitud_historia;
    private String zoom_historia;
    private String descripcion_historia;

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

    public String getLatitud_historia() {
        return latitud_historia;
    }

    public void setLatitud_historia(String latitud_historia) {
        this.latitud_historia = latitud_historia;
    }

    public String getLongitud_historia() {
        return longitud_historia;
    }

    public void setLongitud_historia(String longitud_historia) {
        this.longitud_historia = longitud_historia;
    }

    public String getZoom_historia() {
        return zoom_historia;
    }

    public void setZoom_historia(String zoom_historia) {
        this.zoom_historia = zoom_historia;
    }
}
