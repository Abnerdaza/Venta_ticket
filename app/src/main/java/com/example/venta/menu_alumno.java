package com.example.venta;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.venta.fragmento_alumno.fragmento_alumno_ayuda;
import com.example.venta.fragmento_alumno.fragmento_alumno_comprar;
import com.example.venta.fragmento_alumno.fragmento_alumno_inicio;
import com.example.venta.fragmento_alumno.fragmento_alumno_menu;
import com.example.venta.fragmento_alumno.fragmento_alumno_perfil;
import com.example.venta.fragmento_general.fragmento_general_acerca_de;

public class menu_alumno extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alumno);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        cargar_fragmento(new fragmento_alumno_inicio());
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alumno, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }else if (contador==0){
            Toast.makeText(this,"Presione nuevamente para salir",Toast.LENGTH_SHORT).show();
            contador++;
        }else if(contador!=0){
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000){


            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador=0;
            }
        }.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent (this,MainActivity.class);
            startActivityForResult(intent, 0);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            cargar_fragmento(new fragmento_alumno_inicio());

        } else if (id == R.id.nav_gallery) {
            cargar_fragmento(new fragmento_alumno_perfil());
        } else if (id == R.id.nav_slideshow) {
            cargar_fragmento(new fragmento_alumno_menu());
        } else if (id == R.id.nav_tools) {
            cargar_fragmento(new fragmento_alumno_comprar());
        } else if (id == R.id.nav_share) {
            cargar_fragmento(new fragmento_alumno_ayuda());
        } else if (id == R.id.nav_send) {
            cargar_fragmento(new fragmento_general_acerca_de());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cargar_fragmento(Fragment fragmentento){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor_fragmento,fragmentento).commit();




    }

}
