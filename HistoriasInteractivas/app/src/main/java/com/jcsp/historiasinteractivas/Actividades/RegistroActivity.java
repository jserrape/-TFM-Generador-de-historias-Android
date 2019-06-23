/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:14
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 5:04
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.REST.Respuesta;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private ImageButton gallery;
    private EditText usuario;
    private EditText email;
    private EditText pass;
    private Button botonregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setTitle(R.string.nueva_cuenta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gallery = findViewById(R.id.registroimage);
        gallery.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                selectImage();
            }
        });

        usuario =  findViewById(R.id.registrousuario);
        email =  findViewById(R.id.registroemail);
        pass = findViewById(R.id.registropass);

        botonregistro = findViewById(R.id.botonregistrar);
        botonregistro.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                if(usuario.getText().toString().trim().isEmpty() || email.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.rellenar, Toast.LENGTH_SHORT).show();
                }else{
                    String usu=usuario.getText().toString();
                    String mail = email.getText().toString();
                    String password = cifrarPassword(pass.getText().toString());

                    Bitmap bitmap = ((BitmapDrawable)gallery.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    String imagen = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    GetPostService mAPIService = ApiUtils.getAPIService();
                    mAPIService.crearUsuario(mail, usu, password, imagen).enqueue(new Callback<Respuesta>() {

                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                            Log.d("RespuestaRegistro", response.body().toString());
                            //TODO Cambiar mensaje del servidor en función del codigo que llegue
                            Toast.makeText(getApplicationContext(), response.body().getResulado(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                            startActivityForResult(intent, 0);
                        }

                        @Override
                        public void onFailure(Call<Respuesta> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    private void selectImage() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK ){
            Uri imageUri = data.getData();
            gallery.setImageURI(imageUri);
        }
    }

    private String cifrarPassword(String pass) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] textBytes = new byte[0];
        try {
            textBytes = pass.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        StringBuilder buf = new StringBuilder();
        for (byte b : sha1hash) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

}
