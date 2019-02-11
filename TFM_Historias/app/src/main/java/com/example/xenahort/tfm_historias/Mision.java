package com.example.xenahort.tfm_historias;

public class Mision {
    private String cod_mision;
    private String nombre;
    private String pista;
    private String tipo;
    private String cod_tipo;
    private String icono;
    private String latitud;
    private String longitud;
    private String texto;
    //private int precedentes[];

    public Mision(String cod_mision, String nombre, String pista, String tipo, String cod_tipo, String icono, String latitud, String longitud, String texto) {
        this.cod_mision = cod_mision;
        this.nombre = nombre;
        this.pista = pista;
        this.tipo = tipo;
        this.cod_tipo = cod_tipo;
        this.icono = icono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.texto = texto;
    }

    public String getCod_mision() {
        return cod_mision;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPista() {
        return pista;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCod_tipo() {
        return cod_tipo;
    }

    public String getIcono() {
        return icono;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "cod_mision='" + cod_mision + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pista='" + pista + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cod_tipo='" + cod_tipo + '\'' +
                ", icono='" + icono + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}
