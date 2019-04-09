/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 31/03/19 18:43
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 31/03/19 18:43
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.Mision;

public class FeliciacionUbicacionDialogo {

    private Mision mision;
    private MapFragment map;

    public FeliciacionUbicacionDialogo(final Context contexto, Mision mis, MapFragment mmap){
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_feliciacion_ubicacion);

        this.mision = mis;
        this.map = mmap;

        //Boton aceptar
        Button btn_escalear = (Button) dialogo.findViewById(R.id.empezar_prueba);
        btn_escalear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(mision.getTipo_prueba().equals("qr")){
                    Toast.makeText(dialogo.getContext(), "Ir a qr", Toast.LENGTH_SHORT).show();
                    map.iniciarDialogo(5);
                }else{
                    Toast.makeText(dialogo.getContext(), "Ir a pregunta", Toast.LENGTH_SHORT).show();
                }
                dialogo.dismiss();
            }
        });

        dialogo.show();
    }
}
