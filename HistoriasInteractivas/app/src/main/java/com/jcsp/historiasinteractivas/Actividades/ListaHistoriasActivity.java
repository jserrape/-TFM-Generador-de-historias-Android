package com.jcsp.historiasinteractivas.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.Util.Historia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaHistoriasActivity extends AppCompatActivity {

    private ListaHistoriasActivity clase;

    private ListView lst;
    private String historiasNombres[];
    private ProgressDialog progressDialog;
    private SearchView theFilter;

    private ArrayAdapter<String> adapter;

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
    }

    private void get_historias(){
        GetPostService mAPIService = ApiUtils.getAPIService();
        //Pido las historias al servidor
        mAPIService.getListHistorias().enqueue(new Callback<List<Historia>>() {
            @Override
            public void onResponse(Call<List<Historia>> call, final Response<List<Historia>> response) {
                historiasNombres = new String[response.body().size()];
                int i = 0;

                //Meto los nombres de las histtorias en el array que será el adapter de la lista
                for (Historia post : response.body()) {
                    historiasNombres[i] = post.getNombre_historia();
                    //Toast.makeText(getApplicationContext(), historiasNombres[i], Toast.LENGTH_SHORT).show();
                    ++i;
                }

                //Creo la lista
                lst = findViewById(R.id.listahistorias);
                //TODO Cambiar "clase" por una referencia al padre
                adapter = new ArrayAdapter<String>(clase, android.R.layout.simple_list_item_1, android.R.id.text1, historiasNombres);
                lst.setAdapter(adapter);

                //Creo el listener de seleccionar un elemento
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String itemValue = (String) lst.getItemAtPosition(position);
                        //Toast.makeText(getApplicationContext(), itemValue, Toast.LENGTH_SHORT).show();
                        for(int z=0;z<historiasNombres.length;z++){
                            if(historiasNombres[z]==itemValue){
                                Toast.makeText(getApplicationContext(), "Posición "+z, Toast.LENGTH_SHORT).show();
                                // z es la posición dentro de 'response' -> accedo a él y saco el id
                                int id_hist = response.body().get(z).getId();
                                Toast.makeText(getApplicationContext(), "ID "+response.body().get(z).getId(), Toast.LENGTH_SHORT).show();
                                get_historia(response.body().get(z).getId()+"");
                            }
                        }
                    }
                });

                //Creo el flitro de la lista
                theFilter = (SearchView) findViewById(R.id.searchFilter);
                theFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        (clase).adapter.getFilter().filter(query);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        (clase).adapter.getFilter().filter(newText);
                        return true;
                    }
                });

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Historia>> call, Throwable t) {
            }
        });
    }

    private void get_historia(String id){
        GetPostService mAPIService = ApiUtils.getAPIService();
        mAPIService.solicitud_datos_historia(id).enqueue(new Callback<Historia>() {
            @Override
            public void onResponse(Call<Historia> call, Response<Historia> response) {
                Toast.makeText(getApplicationContext(), "ID "+response.body().getDescripcion_historia(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaHistoriasActivity.this, HistoriaActivity.class);
                intent.putExtra("Historia", response.body());
                startActivityForResult(intent, 0);
            }

            @Override
            public void onFailure(Call<Historia> call, Throwable t) {
            }
        });
    }
}
