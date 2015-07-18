package com.androidatc.androidui;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

// Vamos a realizar 3 tareas para crear el Context Menu
public class ContextMenuActivity extends Activity implements View.OnClickListener {

    RelativeLayout ventana;
    TextView cambiarFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        ventana = (RelativeLayout) findViewById(R.id.ventana);
        cambiarFondo = (TextView) findViewById(R.id.cambiarFondo);


        //2. La siguiente tarea es registrar el context menu con la vista
        //2.1 En nuestro caso queremos asignar context menu a la vista cambiarFondo (TextView)
        registerForContextMenu(cambiarFondo);
    }

    @Override
    public void onClick(View v) {

    }

    //1. Creamos el Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_context_menu, menu);
        menu.setHeaderTitle("Cambiar Fondo de pantalla");
    }

    //3. Manejar los eventos de click sobre los context Menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.amarillo:
                ventana.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.azul:
                ventana.setBackgroundColor(Color.BLUE);
                break;
            case R.id.rojo:
                ventana.setBackgroundColor(Color.RED);
                break;
            case R.id.verde:
                ventana.setBackgroundColor(Color.GREEN);
                break;
            default:
                ventana.setBackgroundColor(Color.MAGENTA);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
