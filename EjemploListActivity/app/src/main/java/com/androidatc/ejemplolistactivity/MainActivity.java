package com.androidatc.ejemplolistactivity;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    static final String[] GrupoA = new String[] {
            "Paraguay",
            "Argentina",
            "Jamaica",
            "Uruaguay"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Obtenemos una referencia al objeto ListView
        ListView lista = getListView();

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GrupoA));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Toast.makeText(this, "La posicion pulsada fue " + position, Toast.LENGTH_LONG).show();

        Toast.makeText(this, "La posicion pulsada fue " + GrupoA[position], Toast.LENGTH_SHORT).show();

    }
}
