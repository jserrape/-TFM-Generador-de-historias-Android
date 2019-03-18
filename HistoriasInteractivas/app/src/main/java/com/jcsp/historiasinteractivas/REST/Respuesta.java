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
