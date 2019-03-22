/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 23/03/19 0:10
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 23/03/19 0:10
 *
 */

package com.jcsp.historiasinteractivas.Util;

import java.io.Serializable;

public class Mision implements Serializable {
    private int id;
    private int id_historia;
    private String nombre_mision;
    private String icono_mision;
    private String latitud_mision;
    private String longitud_mision;
    private String tipo_localizacion;
    private String codigo_localizacion;
    private String tipo_prueba;
    private String codigo_prueba;
    private String descripcion_inicial;
    private String imagen_inicial;
    private String descripcion_final;
    private String imagen_final;
    private String resumen;
    private String precedentes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_historia() {
        return id_historia;
    }

    public void setId_historia(int id_historia) {
        this.id_historia = id_historia;
    }

    public String getNombre_mision() {
        return nombre_mision;
    }

    public void setNombre_mision(String nombre_mision) {
        this.nombre_mision = nombre_mision;
    }

    public String getIcono_mision() {
        return icono_mision;
    }

    public void setIcono_mision(String icono_mision) {
        this.icono_mision = icono_mision;
    }

    public String getLatitud_mision() {
        return latitud_mision;
    }

    public void setLatitud_mision(String latitud_mision) {
        this.latitud_mision = latitud_mision;
    }

    public String getLongitud_mision() {
        return longitud_mision;
    }

    public void setLongitud_mision(String longitud_mision) {
        this.longitud_mision = longitud_mision;
    }

    public String getTipo_localizacion() {
        return tipo_localizacion;
    }

    public void setTipo_localizacion(String tipo_localizacion) {
        this.tipo_localizacion = tipo_localizacion;
    }

    public String getCodigo_localizacion() {
        return codigo_localizacion;
    }

    public void setCodigo_localizacion(String codigo_localizacion) {
        this.codigo_localizacion = codigo_localizacion;
    }

    public String getTipo_prueba() {
        return tipo_prueba;
    }

    public void setTipo_prueba(String tipo_prueba) {
        this.tipo_prueba = tipo_prueba;
    }

    public String getCodigo_prueba() {
        return codigo_prueba;
    }

    public void setCodigo_prueba(String codigo_prueba) {
        this.codigo_prueba = codigo_prueba;
    }

    public String getDescripcion_inicial() {
        return descripcion_inicial;
    }

    public void setDescripcion_inicial(String descripcion_inicial) {
        this.descripcion_inicial = descripcion_inicial;
    }

    public String getImagen_inicial() {
        return imagen_inicial;
    }

    public void setImagen_inicial(String imagen_inicial) {
        this.imagen_inicial = imagen_inicial;
    }

    public String getDescripcion_final() {
        return descripcion_final;
    }

    public void setDescripcion_final(String descripcion_final) {
        this.descripcion_final = descripcion_final;
    }

    public String getImagen_final() {
        return imagen_final;
    }

    public void setImagen_final(String imagen_final) {
        this.imagen_final = imagen_final;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getPrecedentes() {
        return precedentes;
    }

    public void setPrecedentes(String precedentes) {
        this.precedentes = precedentes;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "id=" + id +
                ", id_historia=" + id_historia +
                ", nombre_mision='" + nombre_mision + '\'' +
                //", icono_mision='" + icono_mision + '\'' +
                ", latitud_mision='" + latitud_mision + '\'' +
                ", longitud_mision='" + longitud_mision + '\'' +
                ", tipo_localizacion='" + tipo_localizacion + '\'' +
                ", codigo_localizacion='" + codigo_localizacion + '\'' +
                ", tipo_prueba='" + tipo_prueba + '\'' +
                ", codigo_prueba='" + codigo_prueba + '\'' +
                ", descripcion_inicial='" + descripcion_inicial + '\'' +
                //", imagen_inicial='" + imagen_inicial + '\'' +
                ", descripcion_final='" + descripcion_final + '\'' +
                //", imagen_final='" + imagen_final + '\'' +
                ", resumen='" + resumen + '\'' +
                ", precedentes='" + precedentes + '\'' +
                '}';
    }
}
