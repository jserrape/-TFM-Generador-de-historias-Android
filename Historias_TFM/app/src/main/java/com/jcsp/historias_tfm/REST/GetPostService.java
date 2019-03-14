package com.jcsp.historias_tfm.REST;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetPostService {

    @POST("/rest/usuario")
    @FormUrlEncoded
    Call<Respuesta> crearUsuario(@Field("email") String email,
                                @Field("nombre") String nombre,
                                @Field("password") String password,
                                @Field("imagen_usu") String imagen_usu);

    @POST("/rest/login")
    @FormUrlEncoded
    Call<Respuesta> loginUsuario(@Field("email") String email,
                                 @Field("password") String password);
}