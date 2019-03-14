package com.jcsp.historias_tfm.Actividades;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jcsp.historias_tfm.R;
import com.jcsp.historias_tfm.REST.ApiUtils;
import com.jcsp.historias_tfm.REST.GetPostService;
import com.jcsp.historias_tfm.Util.Historia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaHistoriasActivity extends AppCompatActivity {

    private ListView lst;
    private String historiasNombres[];
    private ListaHistoriasActivity clase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historias);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage(R.string.cargando+"");
        progressDialog.show();

        clase = this;
        get_historias();


        //https://github.com/xenahort/Aplicacion_Android_maps_receptiva_y_adaptable/blob/master/DSS_proyect/app/src/main/java/com/example/xenahort/dss_proyect/Activitys/ListaFarmaciasActivity.java
    }

    private void get_historias(){
        GetPostService mAPIService = ApiUtils.getAPIService();
        mAPIService.getListHistorias().enqueue(new Callback<List<Historia>>() {
            @Override
            public void onResponse(Call<List<Historia>> call, Response<List<Historia>> response) {
                historiasNombres = new String[response.body().size()];
                int i = 0;
                for (Historia post : response.body()) {
                    historiasNombres[i] = post.getNombre_historia();
                    Toast.makeText(getApplicationContext(), historiasNombres[i], Toast.LENGTH_SHORT).show();
                    ++i;
                }
                lst = findViewById(R.id.listahistorias);
                //TODO Cambiar "clase" por una referencia al padre
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(clase, android.R.layout.simple_list_item_1, android.R.id.text1, historiasNombres);
                lst.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Historia>> call, Throwable t) {
            }
        });
    }
}
