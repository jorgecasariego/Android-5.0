package com.androidatc.materialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidatc.materialdesign.R;
import com.androidatc.materialdesign.pojo.Information;

import java.util.Collections;
import java.util.List;

/**
 * Created by jorgecasariego on 14/9/15.
 * Para poder retornar una vista tanto del tipo HeaderHolder como el de ItemHolder
 * lo que hacemos es pasarle al Adapter algo del tipo RecyclerView.ViewHolder (mas general)
 */
public class AdapterDrawer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Information> data= Collections.emptyList();
    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    private LayoutInflater inflater;
    private Context context;
    public AdapterDrawer(Context context, List<Information> data){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    //En este metodo es donde armamos las vistas del recyler view
    //Para el caso del header y los demas items vamos a utilizar el viewType
    //para identificar si la vista a crear es la primera posicion (header) o
    //el resto. Si es el header entonces inflamos el drawer_header sino inflamos el
    //item_drawer
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view=inflater.inflate(R.layout.drawer_header, parent,false);
            HeaderHolder holder=new HeaderHolder(view);
            return holder;
        }
        else{
            View view=inflater.inflate(R.layout.item_drawer, parent,false);
            ItemHolder holder=new ItemHolder(view);
            return holder;
        }

    }

    //Al sobreescribir este metodo lo que hacemos es modificar el funcionamiento
    //del recycler View. Aqui lo que vamos a hacer es decirle al metodo que la
    //posicion 0 del recycler view va a ser el header y el resto si van a ser los
    //items
    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
        else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderHolder ){

        }
        else{
            ItemHolder itemHolder= (ItemHolder) holder;
            Information current=data.get(position-1);
            itemHolder.title.setText(current.title);
            itemHolder.icon.setImageResource(current.iconId);
        }

    }

    //Aqui necesitamos modificar para decirle que existen n items + 1, siendo 1 el header
    @Override
    public int getItemCount() {
        return data.size()+1;
    }

    //Clase para crear el item del recycler view
    class ItemHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        public ItemHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);
        }
    }

    //Clase para crear el header del recycler view
    class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);

        }
    }
}
