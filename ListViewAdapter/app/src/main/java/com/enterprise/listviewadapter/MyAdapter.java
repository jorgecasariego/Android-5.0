package com.enterprise.listviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jorgecasariego on 17/10/15.
 */
public class MyAdapter extends ArrayAdapter<String> {
    Context context;

    String[] titulos;
    String[] subtitulos;

    public MyAdapter(Context context, String[] titulos, String[] subtitulos) {
        super(context, R.layout.single_row, R.id.textView, titulos);

        this.context = context;
        this.titulos = titulos;
        this.subtitulos = subtitulos;

    }


    // Opción 1 para crear vistas
    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        // Hacemos este control para crear la vista si todavía no esta creada. En el caso que sea diferente
        // de null entonces quiere decir que la vista ya esta creada y no hace volver a crear de vuelta
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row,parent, false);
        }


        // Accedemos a la vista de la fila y seteamos con los valores que recibimos en el constructor
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);

        TextView titulo = (TextView) row.findViewById(R.id.textView);
        titulo.setText(titulos[position]);

        TextView subtitulo = (TextView) row.findViewById(R.id.textView2);
        subtitulo.setText(subtitulos[position]);

        return row;

    }*/

    //Opcion 2 usando VieHolder
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder = null;

        // Hacemos este control para crear la vista si todavía no esta creada. En el caso que sea diferente
        // de null entonces quiere decir que la vista ya esta creada y no hace volver a crear de vuelta
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row,parent, false);
            holder = new MyViewHolder(row);

            // Como hacemos para guardar ese holder para poder usar la proxima vez?
            // R: Usando el metodo setTag
            row.setTag(holder);
        } else { //Reciclamos el holder que creamos la primera vez
            holder = (MyViewHolder) row.getTag();
        }


        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.titulo.setText(titulos[position]);
        holder.subtitulo.setText(subtitulos[position]);

        return row;

    }



    class MyViewHolder {
        ImageView imageView;
        TextView titulo;
        TextView subtitulo;
        public MyViewHolder(View v) {
            imageView = (ImageView) v.findViewById(R.id.imageView);
            titulo = (TextView) v.findViewById(R.id.textView);
            subtitulo = (TextView) v.findViewById(R.id.textView2);
        }
    }
}
