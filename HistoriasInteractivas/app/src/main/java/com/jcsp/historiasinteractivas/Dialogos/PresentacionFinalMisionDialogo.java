/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 9/04/19 13:45
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 9/04/19 13:45
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Actividades.LoginActivity;
import com.jcsp.historiasinteractivas.Actividades.RegistroActivity;
import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.REST.Respuesta;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentacionFinalMisionDialogo {

    private Mision mision;
    private MapFragment map;

    @SuppressLint("ResourceAsColor")
    public PresentacionFinalMisionDialogo(final Context contexto, Mision mis, MapFragment mmap) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_presenacion_final_mision);

        this.mision = mis;
        this.map = mmap;

        this.map.eliminarMark();

        //Imagen
        ImageView imgTit = (ImageView) dialogo.findViewById(R.id.imagen_final_historia);
        byte[] imageAsBytes = Base64.decode(mision.getImagen_final().getBytes(), Base64.DEFAULT);
        imgTit.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imgTit.setAdjustViewBounds(true);

        //Texto
        TextView descripcion = (TextView) dialogo.findViewById(R.id.descripcion_final);
        descripcion.setText(mis.getDescripcion_final().replaceAll("#", System.getProperty("line.separator") + System.getProperty("line.separator")));
        descripcion.setMovementMethod(new ScrollingMovementMethod());

        //Boton cerrar
        Button btn_cerrar = (Button) dialogo.findViewById(R.id.cerrar_presenacion_final);
        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        dialogo.show();

        //Indico al servidor que he completado la misión
        SharedPreferences pref = Objects.requireNonNull(contexto).getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        Toast.makeText(contexto, "Email: " + pref.getString("email", "null"), Toast.LENGTH_SHORT).show();

        GetPostService mAPIService = ApiUtils.getAPIService();
        mAPIService.post_completar_mision(pref.getString("email", "null"), mision.getId_historia(), mision.getId()).enqueue(new Callback<Respuesta>() {

            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Toast.makeText(contexto, response.body().getResulado(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(contexto, R.string.error_conexion, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
