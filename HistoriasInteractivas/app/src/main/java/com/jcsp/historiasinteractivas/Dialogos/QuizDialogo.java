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
import android.graphics.Color;
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
    private boolean actuar;

    @SuppressLint("ResourceAsColor")
    public QuizDialogo(final Context contexto, Mision mis, MapFragment mmap) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_quiz);

        this.mision = mis;
        this.map = mmap;
        this.actuar = true;


        //Enunciado
        enunciado = dialogo.findViewById(R.id.enunciado_pregunta);
        enunciado.setText(mis.getPregunta().getEnunciado());

        //Respuestas
        pr1 = dialogo.findViewById(R.id.respuesta_1);
        pr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (actuar) {
                    comprobarRespuesa(v);
                }
            }
        });
        pr2 = dialogo.findViewById(R.id.respuesta_2);
        pr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (actuar) {
                    comprobarRespuesa(v);
                }
            }
        });
        pr3 = dialogo.findViewById(R.id.respuesta_3);
        pr3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (actuar) {
                    comprobarRespuesa(v);
                }
            }
        });
        pr4 = dialogo.findViewById(R.id.respuesta_4);
        pr4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (actuar) {
                    comprobarRespuesa(v);
                }
            }
        });

        //Boton cerrar
        btn_cerrar = dialogo.findViewById(R.id.cencelar_pregunta);
        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!actuar) {
                    map.iniciarDialogo(7);
                    dialogo.dismiss();
                } else {
                    map.iniciarDialogo(7);
                    dialogo.dismiss();
                }
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

    private void comprobarRespuesa(View v) {
        boolean acierto = true;
        switch (v.getId()) {
            case R.id.respuesta_1:
                acierto = comprobarCorrecto(0);
                break;
            case R.id.respuesta_2:
                acierto = comprobarCorrecto(1);
                break;
            case R.id.respuesta_3:
                acierto = comprobarCorrecto(2);
                break;
            case R.id.respuesta_4:
                acierto = comprobarCorrecto(3);
                break;
        }

        if (acierto) {
            acertado(v);
        } else {
            fallado(v);
        }
        actuar = false;
        btn_cerrar.setText(R.string.continuar);
    }

    private boolean comprobarCorrecto(int n) {
        return n == correcto;
    }

    private void acertado(View v) {
        switch (v.getId()) {
            case R.id.respuesta_1:
                pr1.setBackgroundColor(Color.GREEN);
                break;
            case R.id.respuesta_2:
                pr2.setBackgroundColor(Color.GREEN);
                break;
            case R.id.respuesta_3:
                pr3.setBackgroundColor(Color.GREEN);
                break;
            case R.id.respuesta_4:
                pr4.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    private void fallado(View v) {
        switch (correcto) {
            case 0:
                pr1.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                pr2.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                pr3.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                pr4.setBackgroundColor(Color.GREEN);
                break;
        }

        switch (v.getId()) {
            case R.id.respuesta_1:
                pr1.setBackgroundColor(Color.RED);
                break;
            case R.id.respuesta_2:
                pr2.setBackgroundColor(Color.RED);
                break;
            case R.id.respuesta_3:
                pr3.setBackgroundColor(Color.RED);
                break;
            case R.id.respuesta_4:
                pr4.setBackgroundColor(Color.RED);
                break;
        }
    }

}
