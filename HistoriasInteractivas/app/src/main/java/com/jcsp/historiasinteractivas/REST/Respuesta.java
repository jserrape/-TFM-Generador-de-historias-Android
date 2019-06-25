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

    public String getResulado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "status='" + status + '\'' +
                ", resulado='" + resultado + '\'' +
                '}';
    }
}
