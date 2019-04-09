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
import android.graphics.BitmapFactory;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;

public class PresentacionFinalMisionDialogo {

    private Mision mision;

    @SuppressLint("ResourceAsColor")
    public  PresentacionFinalMisionDialogo(final Context contexto, Mision mis){
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_presenacion_final_mision);

        this.mision = mis;


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
    }
}
