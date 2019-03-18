package com.jcsp.historiasinteractivas.Actividades;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
                Intent intent = new Intent(HistoriaActivity.this, MapsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
