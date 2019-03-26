/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 26/03/19 14:47
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 26/03/19 14:47
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.Actividades.CambioPasswordActivity;
import com.jcsp.historiasinteractivas.Actividades.LoginActivity;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.Mision;

public class PresentacionMisionDialogo {

    private Mision mision;

    @SuppressLint("ResourceAsColor")
    public PresentacionMisionDialogo(Context contexto, Mision mis){
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_presentacion_mision);

        this.mision = mis;

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

        Button btn_escalear = (Button) dialogo.findViewById(R.id.escanear_presenacion);

        dialogo.show();
    }
}
