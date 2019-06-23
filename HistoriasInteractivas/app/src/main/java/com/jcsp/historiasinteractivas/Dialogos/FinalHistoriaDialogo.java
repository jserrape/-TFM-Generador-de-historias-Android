/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 19/06/19 19:34
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 19/06/19 19:34
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.jcsp.historiasinteractivas.R;

public class FinalHistoriaDialogo {

    public FinalHistoriaDialogo(final Context contexto) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_final_historia);


        //Imagen
        //ImageView imgTit = (ImageView) dialogo.findViewById(R.id.imagen_final_historia);


        dialogo.show();
    }
}
