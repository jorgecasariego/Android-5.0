package com.androidatc.materialdesignejemplo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jorgecasariego on 2/7/15.
 */
public class MarcadorAdapter extends RecyclerView.Adapter<MarcadorAdapter.ViewHolder>{

    //Creamos primeramente un ArrayList de marcaciones
    private ArrayList<Marcacion> marcacionesList;
    private int itemLayout;

    public MarcadorAdapter(ArrayList<Marcacion> data, int itemLayout) {
        this.marcacionesList = data;
        this.itemLayout = itemLayout;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView avatar;
        public TextView nombre;
        public TextView tipo;
        public TextView hora;

        public ViewHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.image);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            tipo = (TextView) itemView.findViewById(R.id.tipo);
            hora = (TextView) itemView.findViewById(R.id.hora);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Marcacion marcacion = marcacionesList.get(position);

        //Seteamos los valores de las marcaciones segun la posicion
        holder.nombre.setText(marcacion.getNombre());
        holder.tipo.setText(marcacion.getTipo());
        holder.hora.setText(marcacion.getHora());

        if(marcacion.getId() != null) {
            switch (marcacion.getId()) {
                //Aqui habria que controlar que dependiendo del usuario se le pone un avatar
                case 1:
                    holder.avatar.setImageResource(R.drawable.avatar);
                    break;
                case 2:
                    holder.avatar.setImageResource(R.drawable.avatar);
                    break;
                case 3:
                    holder.avatar.setImageResource(R.drawable.avatar);
                    break;
                case 4:
                    holder.avatar.setImageResource(R.drawable.avatar);
                    break;
                case 5:
                    holder.avatar.setImageResource(R.drawable.avatar);
                    break;
            }
        } else {
                //en el caso que sea null se le carga un avatar por default
                holder.avatar.setImageResource(R.drawable.avatar);
        }

        holder.itemView.setTag(marcacion);

    }

    @Override
    public int getItemCount() {
        return this.marcacionesList.size();
    }
}
