package com.androidatc.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by jorgecasariego on 6/7/15.
 *
 * Pasos para manejar los click en Recycler
 * 1. Create a class that extends RecyclerView.OnItemTouchListener
 *
 * 2. Create an interface inside that class that supports click and indicates the View that was
 *    clicked and the position where it was clicked
 *
 * 3. Create a GestureDetector to detect ACTION_UP single tap and long press event
 *
 * 4. Return true from the singleTap to indicate your GestureDetector has consume the event
 *
 * 5. Find the childView containing the coordinates specified by the MotionEvent and if the
 *    childView is not null and the listener is not null either, fire a long click event
 *
 * 6. Use the onInterceptTouchEvent of your RecyclerView to check if the childView is not null,
 *    the listener is not null and the gesture detector consumed the touch event
 *
 * 7. If above condition holds true, fire the event
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private Context context;
    //private ClickListener clickListener;

    public InformationAdapter(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void delete(int position){
        data.remove(position);
        //Notificamos que un item de la lista ha sido removido. Esta funcion es mucho
        //mas eficiente
        notifyItemRemoved(position);
    }

    //  Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    //  This new ViewHolder should be constructed with a new View that can represent the items of the given type.
    //  You can either create a new View manually or inflate it from an XML layout file.
    //  Aqui lo que hacemos es crear la vista y luego pasar el ViewHolder para asociar con sus id's en el custom_row.xml
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //  Called by RecyclerView to display the data at the specified position.
    //  This method should update the contents of the itemView to reflect the item at the given position.
    //  Este metodo despliega los datos en la posicion indicada a partir del holder recibido.
    //  En nuestro caso obtenemos los datos a partir de nuestro modelo Information en la posicion indicada
    //  y lo seteamos en el holder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information information = data.get(position);
        holder.title.setText(information.getTitle());
        holder.icon.setImageResource(information.getIconId());
    }

    /*
    //Tecnica 2
    //Aqui tenemos una referencia al fragment encargado de manejar el click del RecyclerView
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }*/

    @Override
    public int getItemCount() {
        return data.size();
    }


    // A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }

        /*
        @Override
        public void onClick(View v) {
            //Tecnica 1 para el click
            /**
            *  Iniciar una actividad debe ser responsabilidad de la actividad no del adaptador como en este caso
            *  por eso esta  tecnica no es recomendada. Si iniciamos una actividad lo debemos hacer desde
            *  la actividad o desde el fragment
            *
            //context.startActivity(new Intent(context, SubActivity.class));

            //Tecnica 2 para manejo de clicks
            if(clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }*/
    }

    /*
    //Usado para la tecnica 2
    //Lo bueno de la tecnica 2 es que haciendo de esta manera podemos utilizar el mismo adapter
    //para cualquier RecyclerView
    public interface ClickListener{
        public void itemClicked(View v, int position);
    }*/
}
