package com.jcsp.historiasinteractivas.REST;

import com.jcsp.historiasinteractivas.Util.Historia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetPostService {

    /**
     * Crear usuario
     * @param email Email del usuario
     * @param nombre Nombre de usuario
     * @param password Contraseña del usuario
     * @param imagen_usu Imagen de perfil del usuario
     * @return Respuesta del servidor
     */
    @POST("/rest/usuario")
    @FormUrlEncoded
    Call<Respuesta> crearUsuario(@Field("email") String email,
                                 @Field("nombre") String nombre,
                                 @Field("password") String password,
                                 @Field("imagen_usu") String imagen_usu);

    /**
     * Funcion para iniciar sesión
     * @param email Email del usuario
     * @param password Contraseña del usuario
     * @return Respuesta del servidor
     */
    @POST("/rest/login")
    @FormUrlEncoded
    Call<Respuesta> loginUsuario(@Field("email") String email,
                                 @Field("password") String password);

    /**
     * Función para solicitar al servidor cambiar la contraseña
     * @param email Email del usuario
     * @return Respuesta del servidor
     */
    @POST("/rest/cambio")
    @FormUrlEncoded
    Call<Respuesta> solicitud_reseteo_password(@Field("email") String email);

    /**
     * Obtiene una lista básica de las historias (id, nombre_historia)
     * @return Lista de las historias
     */
    @GET("/historia/list")
    Call<List<Historia>> getListHistorias();

    @POST("/historia")
    @FormUrlEncoded
    Call<Historia> solicitud_datos_historia(@Field("id") String id);


}