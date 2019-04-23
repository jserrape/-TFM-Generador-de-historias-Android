/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 23/04/19 22:07
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 23/04/19 22:07
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.jcsp.historiasinteractivas.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class CarouserInicialDialogo {

    private Context contexto;

    private Dialog dialogo;
    private Button btn;

    private CarouselView carousel;
    private int[] imagenes = {R.drawable.default_profile, R.drawable.felicitades, R.drawable.logo_app};

    public CarouserInicialDialogo(Context contexto) {
        dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.blanco));
        dialogo.setContentView(R.layout.dialogo_carousel_inicial);

        this.contexto = contexto;

        carousel = (CarouselView) dialogo.findViewById(R.id.carousel_view_presenacion);
        carousel.setPageCount(imagenes.length);
        carousel.setImageListener(imageListener);

        //Boton aceptar
        btn = (Button) dialogo.findViewById(R.id.button_cerrar_carousel);
        btn.setEnabled(false);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        dialogo.show();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(imagenes[position]);
            Log.d("prueba", position+"");
            if (position == imagenes.length - 1) {
                btn.setEnabled(true);
            }
        }
    };
}
