/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:48
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:47
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Actividades.NavigationDrawerActivity;
import com.jcsp.historiasinteractivas.R;

import java.util.Objects;


public class AjustesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //Elementos de gestion la actividad
    private View vista;
    private Spinner spinner_idioma;
    private Spinner spinner_estilo;
    private Switch switch_centrar;
    private Switch switch_zoom;
    private Switch switch_brujula;
    private Switch segundo_plano;

    //Botones de la actividad
    private Button btn_cancelar;
    private Button btn_guardar;

    //Booleanos para controlar si ha cambiado la configuración
    private Boolean cambio_estilo = false;
    private Boolean cambio_zoom= false;
    private Boolean cambio_centrar= false;
    private Boolean cambio_brujula= false;
    private Boolean cambio_idioma= false;
    private Boolean cambio_segundo_plano= false;

    //Preferencias
    private SharedPreferences prefs;

    public AjustesFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Obtengo los datos de la vista y defino las preferencias
        vista = inflater.inflate(R.layout.fragment_ajustes, container, false);
        prefs = Objects.requireNonNull(this.getActivity()).getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        //Spinner idioma
        spinner_idioma = (Spinner) vista.findViewById(R.id.spinner_idioma);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.idiomas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_idioma.setAdapter(adapter);
        spinner_idioma.setSelection(Integer.parseInt(Objects.requireNonNull(prefs.getString("idioma", "0"))));
        spinner_idioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cambio_idioma = true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        //Spinner estilo
        spinner_estilo = (Spinner) vista.findViewById(R.id.spinner_estilo);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.estilos_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_estilo.setAdapter(adapter2);
        spinner_estilo.setSelection(Integer.parseInt(Objects.requireNonNull(prefs.getString("estilo", "0"))));
        spinner_estilo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cambio_estilo = true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        //Switch centrar
        switch_centrar = (Switch) vista.findViewById(R.id.switch_btn_centrar);
        switch_centrar.setChecked(Boolean.parseBoolean(prefs.getString("centrar","true")));
        switch_centrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cambio_centrar = true;
            }
        });

        //Switch zoom
        switch_zoom = (Switch) vista.findViewById(R.id.switch_btn_zoom);
        switch_zoom.setChecked(Boolean.parseBoolean(prefs.getString("zoom","true")));
        switch_zoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cambio_zoom = true;
            }
        });

        //Switch brújula
        switch_brujula = (Switch) vista.findViewById(R.id.switch_brujula);
        switch_brujula.setChecked(Boolean.parseBoolean(prefs.getString("brujula","true")));
        switch_brujula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cambio_brujula = true;
            }
        });

        //Switch segundo plano
        segundo_plano = (Switch) vista.findViewById(R.id.switch_segundo_plano);
        segundo_plano.setChecked(Boolean.parseBoolean(prefs.getString("segundo_plano","true")));
        segundo_plano.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cambio_segundo_plano = true;
            }
        });

        //Boton cancelar
        btn_cancelar = (Button) vista.findViewById(R.id.btn_ajuses_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cambio_estilo || cambio_idioma || cambio_zoom || cambio_centrar || cambio_brujula || cambio_segundo_plano) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
                    dialogo.setMessage(R.string.eliminar_cambios);
                    dialogo.setCancelable(false);
                    dialogo.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo, int id) {
                            Fragment fragment = new MapFragment();
                            NavigationDrawerActivity nd = (NavigationDrawerActivity) getActivity();
                            nd.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                        }
                    });
                    dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo, int id) {

                        }
                    });
                    dialogo.show();
                }else{
                    Fragment fragment = new MapFragment();
                    NavigationDrawerActivity nd = (NavigationDrawerActivity) getActivity();
                    nd.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                }
            }
        });

        //Boton guardar
        btn_guardar = (Button) vista.findViewById(R.id.btn_ajuses_guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cambio_estilo || cambio_idioma || cambio_zoom || cambio_centrar || cambio_brujula || cambio_segundo_plano) {
                    SharedPreferences.Editor editor = prefs.edit();

                    if (cambio_estilo) {
                        editor.putString("estilo", String.valueOf(spinner_estilo.getSelectedItemPosition()));
                    }

                    if (cambio_idioma) {
                        editor.putString("idioma", String.valueOf(spinner_idioma.getSelectedItemPosition()));
                    }

                    if (cambio_zoom) {
                        editor.putString("zoom", String.valueOf(switch_zoom.isChecked()));
                        Toast.makeText(getContext(), String.valueOf(switch_zoom.isChecked()), Toast.LENGTH_SHORT).show();
                    }

                    if (cambio_centrar) {
                        editor.putString("centrar", String.valueOf(switch_centrar.isChecked()));
                    }

                    if (cambio_brujula) {
                        editor.putString("brujula", String.valueOf(switch_brujula.isChecked()));
                    }

                    if (cambio_segundo_plano) {
                        editor.putString("segundo_plano", String.valueOf(segundo_plano.isChecked()));
                    }
                    editor.commit();
                }
                Fragment fragment = new MapFragment();
                NavigationDrawerActivity nd = (NavigationDrawerActivity)getActivity();
                nd.getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });
        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
