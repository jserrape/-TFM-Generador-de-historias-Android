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
import android.view.Window;

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



        dialogo.show();
    }
}
