package com.jcsp.historias_tfm.Actividades;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historias_tfm.R;
import com.jcsp.historias_tfm.Util.Historia;

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
    }
}
