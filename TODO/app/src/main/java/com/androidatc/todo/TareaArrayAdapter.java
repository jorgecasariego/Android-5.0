package com.androidatc.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jorgecasariego on 12/6/15.
 *
 * Esta clase es un Adapter para representar las tareas en un ListView
 *
 * Lo que hacemos es heredar de BaseAdapter e implementar los metodos que faltan.
 */
public class TareaArrayAdapter extends BaseAdapter {

    /**
     * Aqui guardaremos todas las tareas que queremos mostrar en la lista
     */
    private ArrayList<Tarea> tareas;

    private static LayoutInflater inflater = null;

    public TareaArrayAdapter(Context context, ArrayList<Tarea> tareas) {
        this.tareas = tareas;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Cada vez que agregamos o sacamos un elemento debemos notificarlo
        notifyDataSetChanged();
    }

    /**
     * Este metodo simplemente nos devuelve el numero de tareas de nuestro ListView
     * @return
     */
    @Override
    public int getCount() {
        return tareas.size();
    }

    /**
     * Este metodo nos devuelve una vista (tarea) en una posicion determinada
     * Aqui debemos retornar la tarea en la posicion pasada como parametros
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return tareas.get(position);
    }

    /**
     * Aqui devolvemos el id del elemento. Del Elemento, no del view
     * Por lo general no usamos este metodo a no ser que sean con cursores cuando usamos SQLite databases
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Aqui tenemos que devolver el view a representar.
     * En este metodo nos pasan 3 valores:
     * @param position es la posicion de la tarea que deseamos inflar en la vista
     * @param convertView representa la instancia donde asignaremos el view creado
     * @param parent view padre donde se asignara nuestra tarea, en este caso será ListView
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Lo primero que haremos es comprobar si el View ya existe. Si existe lo reusamos sino lo creamos
        View vista = convertView;

        if(convertView == null){
            vista = inflater.inflate(R.layout.list_row, null);
        }

        ImageView imagen = (ImageView)vista.findViewById(R.id.imagenTareaImageView);
        TextView nombreTarea = (TextView)vista.findViewById(R.id.nombreTareaTextView);
        TextView horaTarea = (TextView)vista.findViewById(R.id.horaTareaTextView);

        Tarea tarea = tareas.get(position);

        imagen.setImageResource(tarea.getCategoria());
        nombreTarea.setText(tarea.getNombre());
        horaTarea.setText(tarea.getHora());

        return vista;

    }
}
