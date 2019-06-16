/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 7/05/19 9:36
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 7/05/19 9:36
 *
 */

package com.jcsp.historiasinteractivas.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorListaPersonalizada extends BaseAdapter {

    protected Activity activity;
    protected List<Mision> items;
    private Context contexto;

    public AdaptadorListaPersonalizada(Activity actividad, List<Mision> items, Context con) {
        this.activity = actividad;
        this.items = items;
        this.contexto = con;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.custom_list_misiones, null);
        }

        //creamos un objeto de la clase WebsEsTl
        Mision mis = items.get(position);

        //Asignamos los recursos a las variable
        TextView titulo = (TextView) v.findViewById(R.id.tvTitulo);
        TextView descripcion = (TextView) v.findViewById(R.id.tvDescripcion);
        ImageView imagen = (ImageView) v.findViewById(R.id.imgWeb);

        //Enviamos informacion a la vista apartir de la informacion que contenga la clase:
        titulo.setText(mis.getNombre_mision());
        if (mis.getNombre_mision() != null && !mis.getCompletado().equals("False")) {
            String[] parts = mis.getCompletado().split(" ");
            descripcion.setText(contexto.getString(R.string.compleado) + " " + parts[0]);
        }else{
            descripcion.setText("");
        }
        //imagen.setImageResource(mis.getImagen());
        byte[] imageAsBytes = Base64.decode(mis.getIcono_mision(), Base64.DEFAULT);
        imagen.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        return v;
    }

}
