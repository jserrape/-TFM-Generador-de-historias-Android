package com.jcsp.historias_tfm.Actividades;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jcsp.historias_tfm.R;

public class RegistroActivity extends AppCompatActivity {

    ImageButton gallery;
    EditText usuario;
    EditText email;
    EditText pass;
    Button botonregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setTitle(R.string.nueva_cuenta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gallery = (ImageButton) findViewById(R.id.registroimage);
        gallery.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                selectImage();
            }
        });

        usuario = (EditText) findViewById(R.id.registrousuario);
        email = (EditText) findViewById(R.id.registroemail);
        pass = (EditText) findViewById(R.id.registropass);

        botonregistro = (Button)findViewById(R.id.botonregistrar);
        botonregistro.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                if(usuario.getText().toString().trim().isEmpty() || email.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.rellenar, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Pulando boton", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void selectImage() {
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
}
