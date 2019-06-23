/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:45
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:45
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Actividades.MainActivity;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.REST.Respuesta;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //Elementos de gestion la actividad
    private View vista;
    private ImageView imagen;
    private EditText email;
    private EditText nombre;
    private EditText pass_actual;
    private EditText pass_nueva;
    private EditText pass_repetir;

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
        imagen = vista.findViewById(R.id.perfil_image);
        byte[] imageAsBytes = Base64.decode(prefs.getString("imagen", "NULL").getBytes(), Base64.DEFAULT);
        imagen.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imagen.setAdjustViewBounds(true);

        //Email
        email = vista.findViewById(R.id.perfil_email);
        email.setText(prefs.getString("email", "NULL"));
        email.setEnabled(false);

        //Nombre
        nombre = vista.findViewById(R.id.perfil_nombre);
        nombre.setText(prefs.getString("nombre", "NULL"));
        nombre.setEnabled(false);

        //Password
        pass_actual = vista.findViewById(R.id.editText_perfil_actual);
        pass_nueva = vista.findViewById(R.id.editText_perfil_nueva);
        pass_repetir = vista.findViewById(R.id.editText_perfil_repetir);

        //Boton cambio pass
        Button btn_cambio = vista.findViewById(R.id.btn_perfil_cambio);
        btn_cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass_actual.getText().toString().trim().isEmpty() || pass_nueva.getText().toString().trim().isEmpty() || pass_repetir.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), R.string.rellenar, Toast.LENGTH_SHORT).show();
                } else {
                    if (pass_nueva.getText().toString().equals(pass_repetir.getText().toString())) {
                        GetPostService mAPIService = ApiUtils.getAPIService();
                        mAPIService.cambiar_password(prefs.getString("email", "NULL"), cifrarPassword(pass_nueva.getText().toString())).enqueue(new Callback<Respuesta>() {
                            @Override
                            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                                Toast.makeText(getContext(), R.string.pass_cambiada, Toast.LENGTH_SHORT).show();
                                pass_actual.setText("");
                                pass_nueva.setText("");
                                pass_repetir.setText("");
                            }

                            @Override
                            public void onFailure(Call<Respuesta> call, Throwable t) {
                                Toast.makeText(getContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), R.string.no_coinciden, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Boton eliminar cuenta
        Button btn_eliminar = vista.findViewById(R.id.btn_perfil_eliminar);
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
                dialogo1.setMessage(R.string.seguro_eliminar);
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        GetPostService mAPIService = ApiUtils.getAPIService();
                        mAPIService.eliminar_usuario(prefs.getString("email", "NULL")).enqueue(new Callback<Respuesta>() {
                            @Override
                            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                            }

                            @Override
                            public void onFailure(Call<Respuesta> call, Throwable t) {
                                Toast.makeText(getContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivityForResult(intent, 0);
                        getActivity().finish();
                    }
                });
                dialogo1.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
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
