package com.androidatc.ejemplolistview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    static final String[] Grupo_A = new String[] {
            "Paraguay",
            "Argentina",
            "Jamaica",
            "Uruguay"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos una referencia al ListView
        final ListView lista_grupo_a = (ListView)findViewById(R.id.listView);

        //ListAdapter es una interfaz que extiende de Adapter el cual es un puente entre el
        //ListView y los datos(en este caso lo que almacena Grupo_A)
        ListAdapter adaptador = new ArrayAdapter<String>(this, R.layout.list_grupo_a, Grupo_A);

        lista_grupo_a.setAdapter(adaptador);

        lista_grupo_a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item seleccionado: " + lista_grupo_a.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
