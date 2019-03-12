package com.jcsp.historias_tfm.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcsp.historias_tfm.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setTitle(R.string.nueva_cuenta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
