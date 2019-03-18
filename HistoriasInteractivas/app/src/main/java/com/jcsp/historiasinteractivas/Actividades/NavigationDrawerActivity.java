/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 12:28
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 12:25
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Fragments.BlueFragment;
import com.jcsp.historiasinteractivas.Fragments.GreenFragment;
import com.jcsp.historiasinteractivas.Fragments.RedFragment;
import com.jcsp.historiasinteractivas.R;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GreenFragment.OnFragmentInteractionListener, RedFragment.OnFragmentInteractionListener, BlueFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment=new BlueFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_camera) {
            Toast.makeText(getApplicationContext(), "Toast por camera", Toast.LENGTH_SHORT).show();
            miFragment=new BlueFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), "Toast por galeria", Toast.LENGTH_SHORT).show();
            miFragment=new RedFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(), "Toast por nav_slideshow", Toast.LENGTH_SHORT).show();
            miFragment=new GreenFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(), "Toast por nav_manage", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), "Toast por nav_share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(), "Toast por nav_send", Toast.LENGTH_SHORT).show();
        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
