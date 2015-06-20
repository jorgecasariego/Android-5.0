package com.androidatc.fragmentlistcolores;


import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends ListFragment {

    int colors[] = new int[] {
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE
    };

    String nombre_colores[] = new String[] {
            "Rojo",
            "Verde",
            "Amarillo",
            "Azul"
    };


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragment1, container);

        //ListView lista = getListView();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nombre_colores);


        setListAdapter(adaptador);


        return vista;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Fragment2 fragmento_dos = (Fragment2)getFragmentManager().findFragmentById(R.id.fragment2);
        fragmento_dos.setImageViewBackground(colors[position]);

        super.onListItemClick(l, v, position, id);
    }
}
