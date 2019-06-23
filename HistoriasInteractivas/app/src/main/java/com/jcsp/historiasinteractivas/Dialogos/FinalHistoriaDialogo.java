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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.R;

public class FinalHistoriaDialogo {

    public FinalHistoriaDialogo(final Context contexto, String nombre_historia) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_final_historia);

        TextView txt_cuerpo = dialogo.findViewById(R.id.felicitacion_final_historia);
        txt_cuerpo.setText(contexto.getText(R.string.final1) + " " + nombre_historia + " " + contexto.getText(R.string.final2));
        Button btn_cerrar = dialogo.findViewById(R.id.cerrar_felicitacion_final_historia);
        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        dialogo.show();
    }
}
