package com.enterprise.listviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] titulos;
    String[] subtitulos;

    ListView lista;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulos = getResources().getStringArray(R.array.titulo);
        subtitulos = getResources().getStringArray(R.array.subtitulo);

        lista = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(this, titulos, subtitulos);
        lista.setAdapter(adapter);

    }
}
