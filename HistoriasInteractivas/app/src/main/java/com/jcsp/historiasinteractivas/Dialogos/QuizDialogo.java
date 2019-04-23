/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 23/04/19 19:23
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 23/04/19 19:23
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;

import java.util.ArrayList;
import java.util.List;

public class QuizDialogo {

    private Mision mision;
    private MapFragment map;

    private TextView enunciado;
    private Button pr1;
    private Button pr2;
    private Button pr3;
    private Button pr4;
    private Button btn_cerrar;

    private int correcto;

    @SuppressLint("ResourceAsColor")
    public QuizDialogo(final Context contexto, Mision mis, MapFragment mmap) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_quiz);

        this.mision = mis;
        this.map = mmap;


        //Enunciado
        enunciado = (TextView) dialogo.findViewById(R.id.enunciado_pregunta);
        enunciado.setText(mis.getPregunta().getEnunciado());

        //Respuestas
        pr1 = (Button) dialogo.findViewById(R.id.respuesta_1);
        pr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesa();
            }
        });
        pr2 = (Button) dialogo.findViewById(R.id.respuesta_2);
        pr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesa();
            }
        });
        pr3 = (Button) dialogo.findViewById(R.id.respuesta_3);
        pr3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesa();
            }
        });
        pr4 = (Button) dialogo.findViewById(R.id.respuesta_4);
        pr4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesa();
            }
        });

        //Boton cerrar
        btn_cerrar = (Button) dialogo.findViewById(R.id.cencelar_pregunta);
        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        organizarAleatorio();
        dialogo.show();
    }

    private void organizarAleatorio() {
        List<Button> list = new ArrayList<Button>();
        list.add(pr1);
        list.add(pr2);
        list.add(pr3);
        list.add(pr4);

        correcto = (int) (Math.random() * list.size() - 1);

        list.remove(correcto).setText(mision.getPregunta().getRespues_correcta());

        int aux = (int) (Math.random() * list.size() - 1);
        list.remove(aux).setText(mision.getPregunta().getRespues_incorrecta_1());

        aux = (int) (Math.random() * list.size() - 1);
        list.remove(aux).setText(mision.getPregunta().getRespues_incorrecta_2());

        list.remove(0).setText(mision.getPregunta().getRespues_incorrecta_3());
    }

    private void comprobarRespuesa() {

    }

}
