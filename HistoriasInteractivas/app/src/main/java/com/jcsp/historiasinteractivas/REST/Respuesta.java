/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:14
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 4:46
 *
 */

package com.jcsp.historiasinteractivas.REST;

public class Respuesta {

    private String status;
    private String resultado;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResulado() {
        return resultado;
    }

    public void setResulado(String resulado) {
        this.resultado = resulado;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "status='" + status + '\'' +
                ", resulado='" + resultado + '\'' +
                '}';
    }
}
