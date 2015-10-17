package com.androidatc.ejemplolistactivity;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * El manejo de listas se puede hacer de dos maneras:
 * 1. Al extender de ListActivity la actividad ya tiene disponible una lista simple que simplemente
 *    podemos usar
 * 2. Agregando un ListView en nuestro layout con el id: @android:id/list y luego usarlo aquí
 */
public class MainActivity extends ListActivity {

    static final String[] GrupoA = new String[] {
            "Paraguay",
            "Argentina",
            "Brasil",
            "Uruaguay"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Obtenemos una referencia al objeto ListView
        //Ya no hace falta hacer findViewById()
        ListView lista = getListView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GrupoA);

        lista.setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Toast.makeText(this, "La posicion pulsada fue " + position, Toast.LENGTH_LONG).show();
        TextView vista = (TextView) v;

        Toast.makeText(this, "La posicion pulsada fue " + GrupoA[position] + " : "  + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();

    }
}
