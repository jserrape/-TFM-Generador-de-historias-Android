/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:15
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 8:09
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.Historia;

public class HistoriaActivity extends AppCompatActivity {

    private Historia historia;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        getSupportActionBar().hide();

        historia = (Historia) getIntent().getSerializableExtra("Historia");

        //Titulo
        ImageView imgTit = (ImageView) findViewById(R.id.imageViewTituloHistoria);
        byte[] imageAsBytes = Base64.decode(historia.getImagen_historia().getBytes(), Base64.DEFAULT);
        imgTit.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imgTit.setAdjustViewBounds(true);

        //Descripción
        TextView textDes = (TextView) findViewById(R.id.textViewDescripcionHistoria);
        textDes.setText(historia.getDescripcion_historia().replaceAll("salto",System.getProperty("line.separator")+System.getProperty("line.separator")));
        textDes.setMovementMethod(new ScrollingMovementMethod());

        //Boton
        Button btnRegistro = (Button) findViewById(R.id.buttonEmpezarHistoria);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoriaActivity.this, NavigationDrawerActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        soliciarPermisoLocalizacion();
    }

    private void soliciarPermisoLocalizacion(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Explicamos porque necesitamos el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                // El usuario no necesitas explicación, puedes solicitar el permiso:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    }
}
