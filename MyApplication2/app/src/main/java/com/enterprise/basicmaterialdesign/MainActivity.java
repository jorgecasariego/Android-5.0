package com.enterprise.basicmaterialdesign;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1. Toolbar
    private Toolbar toolbar;

    // 2. Drawer
    private DrawerLayout drawer;

    // 3. Drawer Toggle
    private ActionBarDrawerToggle drawerToggle;

    // 4. Creamos un ListView para simplificar
    ListView lista;
    static final String[] Grupo_A = new String[] {
            "Paraguay",
            "Argentina",
            "Brasil",
            "Uruguay",
            "Paraguay",
            "Argentina",
            "Brasil",
            "Paraguay",
            "Argentina",
            "Brasil",
            "Paraguay",
            "Argentina",
            "Brasil"
    };

    // 5. Collapsing toolbar
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 1. Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. Drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 3. Drawer Toggle
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // 4 ListView
        lista = (ListView) findViewById(R.id.lista);
        ListAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Grupo_A);
        lista.setAdapter(adaptador);

        // 5. Collapsing toolbar
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("ATC");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
