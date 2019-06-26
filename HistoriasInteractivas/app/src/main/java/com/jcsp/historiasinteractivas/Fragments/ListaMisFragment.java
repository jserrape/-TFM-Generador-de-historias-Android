/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:44
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:44
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Actividades.ListaHistoriasActivity;
import com.jcsp.historiasinteractivas.Actividades.LoginActivity;
import com.jcsp.historiasinteractivas.Actividades.NavigationDrawerActivity;
import com.jcsp.historiasinteractivas.Actividades.SplashActivity;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.REST.Respuesta;
import com.jcsp.historiasinteractivas.Util.AdaptadorListaPersonalizada;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaMisFragment extends Fragment {

    private NavigationDrawerActivity nd;

    private List<Mision> misions;
    private View vista;
    private int id_historia;

    private ListView listView;


    public ListaMisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_misiones, container, false);

        if (getArguments() != null) {
            misions = (List<Mision>) getArguments().getSerializable("elist");
            nd = (NavigationDrawerActivity) getArguments().getSerializable("nd");
            id_historia = getArguments().getInt("id_historia");
        }

        listView = vista.findViewById(R.id.lista_fragment_misiones);

        List<Mision> misions_completadas = new ArrayList<>();
        for (int i = 0; i < misions.size(); i++) {
            if (!misions.get(i).getCompletado().equals("False")) {
                misions_completadas.add(misions.get(i));
            }
        }
        AdaptadorListaPersonalizada adaptador = new AdaptadorListaPersonalizada(getActivity(), misions_completadas, getContext());
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nd.irAMision(position);
            }
        });

        final FragmentActivity fr = this.getActivity();
        Button btn_reinicio = vista.findViewById(R.id.btn_reiniciar);
        btn_reinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setMessage(getString(R.string.reiniciar_dialogo));
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton(getString(R.string.reiniciar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        SharedPreferences prefs = Objects.requireNonNull(fr).getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
                        GetPostService mAPIService = ApiUtils.getAPIService();
                        String mail = prefs.getString("email", "NULL");
                        mAPIService.reiniciar_progreso_historia(mail, id_historia).enqueue(new Callback<Respuesta>() {
                            @Override
                            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                                Intent intent = new Intent(nd, ListaHistoriasActivity.class);
                                startActivity(intent);
                                nd.finish();
                            }

                            @Override
                            public void onFailure(Call<Respuesta> call, Throwable t) {
                            }
                        });
                    }
                });
                dialogo1.setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
            }
        });
        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
    }
}
