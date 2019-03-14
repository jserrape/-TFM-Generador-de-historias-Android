package com.jcsp.historias_tfm.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historias_tfm.R;
import com.jcsp.historias_tfm.REST.ApiUtils;
import com.jcsp.historias_tfm.REST.GetPostService;
import com.jcsp.historias_tfm.REST.Respuesta;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private TextView passOlvidada;
    private Button btnAcceso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.acceso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = (EditText) findViewById(R.id.loginemail);
        pass = (EditText) findViewById(R.id.loginpass);
        passOlvidada = (TextView) findViewById(R.id.olvidarpass);
        btnAcceso = (Button) findViewById(R.id.botonlogin);

        passOlvidada.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TODO Cambiar por acividad para cambio de password
                Toast.makeText(getApplicationContext(), "Cambiar por acividad para cambio de password", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btnAcceso.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(email.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.rellenar, Toast.LENGTH_SHORT).show();
                }else{
                    String mail = email.getText().toString();
                    String password = cifrarPassword(pass.getText().toString());

                    GetPostService mAPIService = ApiUtils.getAPIService();
                    mAPIService.loginUsuario(mail, password).enqueue(new Callback<Respuesta>() {

                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                            Log.d("RespuestaRegistro", response.body().toString());
                            if(Integer.parseInt(response.body().getStatus())==200){
                                Toast.makeText(getApplicationContext(), "Inicio correcto", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Inicio mal", Toast.LENGTH_SHORT).show();
                            }
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


    private String cifrarPassword(String pass){
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
