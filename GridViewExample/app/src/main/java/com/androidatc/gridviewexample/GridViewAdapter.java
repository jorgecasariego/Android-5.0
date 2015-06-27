package com.androidatc.gridviewexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jorgecasariego on 26/6/15.
 *
 * El SDK de Android nos provee de 3 diferentes tipos de implementaciones de adaptadores:
 * ArrayAdapter, CursorAdapter y SimpleAdapter.
 *
 * ArrayAdapter espera un Array o una lista como entrada,
 * CursorAdapter acepta instancias de un cursor y
 * SimpleAdapter mapea datos estaticos definidos en los recursos.
 *
 * El tipo de adaptador que se adecue a las necesidades de nuestra app
 * depende puramente del tipo de dato de entrada.
 *
 * BaseAdapteres la implementacion generica de los 3 tipos de adaptadores citados mas arriba
 * y esta puede ser usada para ListViews, GridViews o Spinners.
 *
 * Como ejemplo, podriamos usar directamente un ArrayAdapter pasandoles un array como entrada
 * o podriamos crear nuestra propia clase extendiendo de BaseAdapter
 */

public class GridViewAdapter extends ArrayAdapter{

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = (ImageItem) data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());

        return row;

    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}
