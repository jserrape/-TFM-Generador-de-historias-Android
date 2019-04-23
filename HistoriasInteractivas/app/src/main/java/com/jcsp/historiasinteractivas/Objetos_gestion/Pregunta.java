/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 22/04/19 18:36
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 22/04/19 18:35
 *
 */

package com.jcsp.historiasinteractivas.Objetos_gestion;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private String id;
    private String enunciado;
    private String respues_correcta;
    private String respues_incorrecta_1;
    private String respues_incorrecta_2;
    private String respues_incorrecta_3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespues_correcta() {
        return respues_correcta;
    }

    public void setRespues_correcta(String respues_correcta) {
        this.respues_correcta = respues_correcta;
    }

    public String getRespues_incorrecta_1() {
        return respues_incorrecta_1;
    }

    public void setRespues_incorrecta_1(String respues_incorrecta_1) {
        this.respues_incorrecta_1 = respues_incorrecta_1;
    }

    public String getRespues_incorrecta_2() {
        return respues_incorrecta_2;
    }

    public void setRespues_incorrecta_2(String respues_incorrecta_2) {
        this.respues_incorrecta_2 = respues_incorrecta_2;
    }

    public String getRespues_incorrecta_3() {
        return respues_incorrecta_3;
    }

    public void setRespues_incorrecta_3(String respues_incorrecta_3) {
        this.respues_incorrecta_3 = respues_incorrecta_3;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id='" + id + '\'' +
                ", enunciado='" + enunciado + '\'' +
                ", respues_correcta='" + respues_correcta + '\'' +
                ", respues_incorrecta_1='" + respues_incorrecta_1 + '\'' +
                ", respues_incorrecta_2='" + respues_incorrecta_2 + '\'' +
                ", respues_incorrecta_3='" + respues_incorrecta_3 + '\'' +
                '}';
    }
}
