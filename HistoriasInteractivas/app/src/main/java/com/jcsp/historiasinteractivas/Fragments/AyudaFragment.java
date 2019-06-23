/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:48
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:48
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.jcsp.historiasinteractivas.R;

public class AyudaFragment extends Fragment {

    //Elementos de gestion la actividad
    private View vista;
    private WebView web;

    public AyudaFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ayuda, container, false);

        web = vista.findViewById(R.id.terminosweb);
        web.loadUrl("file:///android_asset/terminos.html");

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
