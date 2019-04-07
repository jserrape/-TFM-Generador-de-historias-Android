/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 26/03/19 14:47
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 26/03/19 14:47
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Actividades.CambioPasswordActivity;
import com.jcsp.historiasinteractivas.Actividades.LoginActivity;
import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.Mision;

import java.io.IOException;

public class PresentacionMisionDialogo {

    private Mision mision;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private MapFragment map;

    @SuppressLint("ResourceAsColor")
    public PresentacionMisionDialogo(final Context contexto, Mision mis, MapFragment mmap){
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_presentacion_mision);

        this.mision = mis;
        this.map = mmap;

        //Titulo
        TextView titulo = (TextView) dialogo.findViewById(R.id.titulo_mision_presentacion);
        titulo.setText(mis.getNombre_mision());

        //Imagen
        ImageView imgTit = (ImageView) dialogo.findViewById(R.id.imagen_inicial_historia);
        byte[] imageAsBytes = Base64.decode(mision.getImagen_inicial().getBytes(), Base64.DEFAULT);
        imgTit.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imgTit.setAdjustViewBounds(true);

        //Texto
        TextView descripcion = (TextView) dialogo.findViewById(R.id.descripcion_inicial);
        descripcion.setText(mis.getDescripcion_inicial().replaceAll("#",System.getProperty("line.separator")+System.getProperty("line.separator")));
        descripcion.setMovementMethod(new ScrollingMovementMethod());

        //Boton cancelar
        Button btn_cancelar = (Button) dialogo.findViewById(R.id.cancelar_presenacion);
        btn_cancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialogo.dismiss();
            }
        });

        //Boton aceptar
        Button btn_escalear = (Button) dialogo.findViewById(R.id.escanear_presenacion);
        btn_escalear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(mision.getTipo_localizacion().equals("qr")){
                    Toast.makeText(dialogo.getContext(), "Ir a qr", Toast.LENGTH_SHORT).show();
                    map.iniciarDialogo(2);
                }else{
                    Toast.makeText(dialogo.getContext(), "Ir a beacon", Toast.LENGTH_SHORT).show();
                }
                dialogo.dismiss();
            }
        });

        dialogo.show();

        soliciarPermisoCamara(contexto);
    }

    private void soliciarPermisoCamara(Context contexto){
        if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Explicamos porque necesitamos el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) contexto, Manifest.permission.CAMERA)) {
            } else {
                // El usuario no necesitas explicación, puedes solicitar el permiso:
                ActivityCompat.requestPermissions((Activity) contexto, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }
}
