package com.example.xenahort.tfm_historias.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xenahort.tfm_historias.Historia;
import com.example.xenahort.tfm_historias.R;
import com.example.xenahort.tfm_historias.VariablesMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class PresentacionHistoriaActivity extends AppCompatActivity {

    private Historia historia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion_historia);
        solicitarAccesoUbicacion();
        crearHistoria();

        ImageButton carritoButton = (ImageButton) findViewById(R.id.imageButton);
        carritoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresentacionHistoriaActivity.this, MapsActivity.class);
                intent.putExtra("Historia", historia);
                startActivityForResult(intent, 0);
            }
        });

        TextView entry = (TextView) findViewById(R.id.textViewDescripcionHistoria);
        entry.setText(this.historia.getDescripcion());

        ImageView imageview= (ImageView)findViewById(R.id.imageViewTitulo);
        imageview.setImageResource(this.getResources().getIdentifier(historia.getImagenTitulo(), "drawable", this.getPackageName()));
    }

    private void crearHistoria() {
        int drawableResourceId = this.getResources().getIdentifier("fichero", "raw", this.getPackageName());

        InputStream inputStream = getResources().openRawResource(drawableResourceId);
        String txt = "";
        try {
            byte[] buffer = new byte[inputStream.available()];
            while (inputStream.read(buffer) != -1) {
                txt = new String(buffer);
            }
            JSONObject object = new JSONObject(txt);
            JSONObject objectaux = new JSONObject(object.getString("Camara inicial"));
            this.historia = new Historia(object.getString("Codigo"),
                    object.getString("Descripcion"),
                    object.getString("Nombre historia"),
                    object.getString("Zoom inicial"),
                    object.getString("Imagen de titulo"),
                    objectaux.getString("Longitud inicial"),
                    objectaux.getString("Latitud inicial"));
            JSONArray json_array = object.optJSONArray("Misiones");
            for (int i = 0; i < json_array.length(); i++) {
                historia.nuevaMision(json_array.getJSONObject(i).toString());
            }
            Log.d("tostringhistoria", this.historia.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void solicitarAccesoUbicacion() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), VariablesMap.FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), VariablesMap.COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                VariablesMap.mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(this, permissions, VariablesMap.LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, VariablesMap.LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
}
