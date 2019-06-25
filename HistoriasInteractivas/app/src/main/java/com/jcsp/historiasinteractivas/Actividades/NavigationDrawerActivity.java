/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:28
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:25
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.Fragments.AjustesFragment;
import com.jcsp.historiasinteractivas.Fragments.AyudaFragment;
import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.Fragments.ListaMisFragment;
import com.jcsp.historiasinteractivas.Fragments.MisionFragment;
import com.jcsp.historiasinteractivas.Fragments.PerfilFragment;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Objetos_gestion.Historia;

import java.io.Serializable;

public class NavigationDrawerActivity extends AppCompatActivity
        implements Serializable, NavigationView.OnNavigationItemSelectedListener, ListaMisFragment.OnFragmentInteractionListener, PerfilFragment.OnFragmentInteractionListener, AjustesFragment.OnFragmentInteractionListener, AyudaFragment.OnFragmentInteractionListener {

    private ImageView imagen;
    private TextView nav_user;
    private TextView nav_mail;

    private Historia historia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        historia = (Historia) getIntent().getSerializableExtra("Historia");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().hide();

        //Inicializo los objetos
        SharedPreferences prefs = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        View hView = navigationView.getHeaderView(0);
        nav_user = hView.findViewById(R.id.text_nombre_nd);
        nav_user.setText(prefs.getString("nombre", "NULL"));

        nav_mail = hView.findViewById(R.id.text_email_nd);
        nav_mail.setText(prefs.getString("email", "NULL"));

        imagen = hView.findViewById(R.id.imageView_nd);
        byte[] imageAsBytes = Base64.decode(prefs.getString("imagen", "NULL").getBytes(), Base64.DEFAULT);
        imagen.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imagen.setAdjustViewBounds(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.nav_mapa) {
            fragment = new MapFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_perfil) {
            fragment = new PerfilFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_misiones) {
            fragment = new ListaMisFragment();
            Bundle bundl = new Bundle();
            bundl.putSerializable("elist", (Serializable) historia.getMisiones());
            bundl.putSerializable("nd", this);
            bundl.putInt("id_historia", this.historia.getId());
            fragment.setArguments(bundl);
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_ajustes) {
            fragment = new AjustesFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_ayuda) {
            fragment = new AyudaFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_cerrar_sesión) {
            SharedPreferences prefs = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", "null");
            editor.putString("nombre", "null");
            editor.putString("imagen", "null");
            editor.commit();
            Intent intent = new Intent(NavigationDrawerActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
            finish();
        }

        if (fragmentSeleccionado == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void irAMision(int n) {
        Fragment fragment = new MisionFragment();
        Bundle bundl = new Bundle();
        bundl.putSerializable("mision", historia.getMisiones().get(n));
        bundl.putSerializable("nd", this);
        fragment.setArguments(bundl);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    public void irALista() {
        Fragment fragment = new ListaMisFragment();
        Bundle bundl = new Bundle();
        bundl.putSerializable("elist", (Serializable) historia.getMisiones());
        bundl.putSerializable("nd", this);
        fragment.setArguments(bundl);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }


    public Historia getHistoria() {
        return historia;
    }

}
