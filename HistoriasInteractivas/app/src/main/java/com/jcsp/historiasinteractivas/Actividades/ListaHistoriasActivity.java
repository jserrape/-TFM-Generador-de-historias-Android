/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:15
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 5:08
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.Objetos_gestion.Historia;

import java.util.List;
import java.util.Objects;

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
        progressDialog.setMessage(getString(R.string.cargando));
        progressDialog.show();

        clase = this;
        get_historias();
    }

    private void get_historias() {
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
                    ++i;
                }

                //Creo la lista
                lst = findViewById(R.id.listahistorias);
                adapter = new ArrayAdapter<>(clase, android.R.layout.simple_list_item_1, android.R.id.text1, historiasNombres);
                lst.setAdapter(adapter);

                //Creo el listener de seleccionar un elemento
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String itemValue = (String) lst.getItemAtPosition(position);
                        for (int z = 0; z < historiasNombres.length; z++) {
                            if (historiasNombres[z] == itemValue) {
                                get_historia(response.body().get(z).getId() + "");
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

    private void get_historia(String id) {
        SharedPreferences pref = Objects.requireNonNull(this).getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        GetPostService mAPIService = ApiUtils.getAPIService();
        mAPIService.solicitud_datos_historia(id, pref.getString("email", "null")).enqueue(new Callback<Historia>() {
            @Override
            public void onResponse(Call<Historia> call, Response<Historia> response) {
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
