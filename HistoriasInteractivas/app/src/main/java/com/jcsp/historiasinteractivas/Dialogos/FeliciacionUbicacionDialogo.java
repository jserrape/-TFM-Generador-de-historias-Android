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
import android.view.Window;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.Mision;

public class FeliciacionUbicacionDialogo {

    private Mision mision;

    public FeliciacionUbicacionDialogo(final Context contexto, Mision mis){
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_feliciacion_ubicacion);

        this.mision = mis;

        dialogo.show();
    }
}
