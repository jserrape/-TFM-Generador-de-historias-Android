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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

public class CarouserInicialDialogo {

    private Context contexto;

    private Dialog dialogo;
    private Button btn;

    private CarouselView carousel;
    private int[] imagenes = {R.drawable.planeta, R.drawable.satelite, R.drawable.camara};

    public CarouserInicialDialogo(final Context contexto) {
        dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_carousel_inicial);

        this.contexto = contexto;

        carousel = (CarouselView) dialogo.findViewById(R.id.carousel_view_presenacion);
        carousel.setPageCount(imagenes.length);
        carousel.setViewListener(viewListener);

        //Boton aceptar
        btn = (Button) dialogo.findViewById(R.id.button_cerrar_carousel);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        dialogo.show();
    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View customView = inflater.inflate(R.layout.custom_view_carouser_presentacion, null);

            TextView textView1 = (TextView) customView.findViewById(R.id.textView_titulo_carousel);
            ImageView imageView = (ImageView) customView.findViewById(R.id.imageView_carousel_presentacion);
            TextView textView2 = (TextView) customView.findViewById(R.id.textView_texto_carousel);

            switch (position){
                case 0:
                    textView1.setText(R.string.bienvenido);
                    imageView.setImageResource(imagenes[position]);
                    textView2.setText(R.string.bienvenido2);
                    break;
                case 1:
                    textView1.setText(R.string.ubicacion);
                    imageView.setImageResource(imagenes[position]);
                    textView2.setText(R.string.ubicacion2);
                    break;
                case 2:
                    textView1.setText(R.string.camara);
                    imageView.setImageResource(imagenes[position]);
                    textView2.setText(R.string.camara2);
                    break;
            }

            return customView;
        }
    };
}
