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
import android.graphics.BitmapFactory;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.Util.Permisos;

public class PresentacionMisionDialogo {

    private Mision mision;
    private MapFragment map;

    @SuppressLint("ResourceAsColor")
    public PresentacionMisionDialogo(final Context contexto, Mision mis, MapFragment mmap) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_presentacion_mision);

        this.mision = mis;
        this.map = mmap;

        //Titulo
        TextView titulo = dialogo.findViewById(R.id.titulo_mision_presentacion);
        titulo.setText(mis.getNombre_mision());

        //Imagen
        ImageView imgTit = dialogo.findViewById(R.id.imagen_inicial_historia);
        byte[] imageAsBytes = Base64.decode(mision.getImagen_inicial().getBytes(), Base64.DEFAULT);
        imgTit.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imgTit.setAdjustViewBounds(true);

        //Texto
        TextView descripcion = dialogo.findViewById(R.id.descripcion_inicial);
        descripcion.setText(mis.getDescripcion_inicial().replaceAll("#", System.getProperty("line.separator") + System.getProperty("line.separator")));
        descripcion.setMovementMethod(new ScrollingMovementMethod());

        //Boton cancelar
        Button btn_cancelar = dialogo.findViewById(R.id.cancelar_presenacion);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        //Boton aceptar
        Button btn_escalear = dialogo.findViewById(R.id.escanear_presenacion);
        btn_escalear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mision.getTipo_localizacion().equals("qr")) {
                    map.iniciarDialogo(2);
                    (new Permisos()).soliciarPermisoCamara(contexto);
                } else {
                    Toast.makeText(dialogo.getContext(), "Ir a beacon", Toast.LENGTH_SHORT).show();
                }
                dialogo.dismiss();
            }
        });

        if (mision.getTipo_localizacion().equals("qr")) {
            (new Permisos()).soliciarPermisoCamara(contexto);
        }
        dialogo.show();
    }
}
