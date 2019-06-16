/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 16/06/19 20:15
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 16/06/19 20:15
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.R;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent;
                SharedPreferences prefs = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
                String aux = prefs.getString("email", "null");
                Toast toast1 = Toast.makeText(getApplicationContext(), aux, Toast.LENGTH_SHORT);
                toast1.show();
                if (aux.equals("null")) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, ListaHistoriasActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}
