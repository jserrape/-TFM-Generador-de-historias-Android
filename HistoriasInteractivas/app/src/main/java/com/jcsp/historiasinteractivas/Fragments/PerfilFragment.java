/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:45
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:45
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jcsp.historiasinteractivas.R;

import java.util.Objects;


public class PerfilFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //Elementos de gestion la actividad
    private View vista;
    private ImageView imagen;
    private EditText email;
    private EditText nombre;

    //Botones de la actividad

    //Preferencias
    private SharedPreferences prefs;

    public PerfilFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Obtengo los datos de la vista y defino las preferencias
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        prefs = Objects.requireNonNull(this.getActivity()).getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        //Imagen
        imagen = (ImageView) vista.findViewById(R.id.perfil_image);
        byte[] imageAsBytes = Base64.decode(prefs.getString("imagen", "NULL").getBytes(), Base64.DEFAULT);
        imagen.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imagen.setAdjustViewBounds(true);

        //Email
        email = (EditText) vista.findViewById(R.id.perfil_email);
        email.setText(prefs.getString("email", "NULL"));
        email.setEnabled(false);

        //Nombre
        nombre = (EditText) vista.findViewById(R.id.perfil_nombre);
        nombre.setText(prefs.getString("nombre", "NULL"));
        nombre.setEnabled(false);

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
    }
}
