/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:44
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:44
 *
 */

package com.jcsp.historiasinteractivas.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Util.AdaptadorListaPersonalizada;

import java.util.List;


public class MisionesFragment extends Fragment {

    private List<Mision> misions;
    private View vista;

    private ListView listView;

    public MisionesFragment() {
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
        }

        listView = (ListView) vista.findViewById(R.id.lista_fragment_misiones);

        AdaptadorListaPersonalizada adaptador = new AdaptadorListaPersonalizada(getActivity(), misions);
        listView.setAdapter(adaptador);

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
