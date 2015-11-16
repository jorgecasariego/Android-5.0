package com.enterprise.alertdialogmaterial;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

//Vamos a realizar 3 tareas para crear el context menu
public class ContextMenuActivity extends AppCompatActivity {

    private RelativeLayout ventana;
    private TextView cambiarColorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        ventana = (RelativeLayout) findViewById(R.id.ventana);
        cambiarColorView = (TextView) findViewById(R.id.cambiar_color);

        //2.   Registrar rel context menu
        registerForContextMenu(cambiarColorView);

    }

    //1. Crear el context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //1.1  Crear el menu de colores que va a mostrar el context menu
        getMenuInflater().inflate(R.menu.menu_context_menu, menu);
        menu.setHeaderTitle("Cambiar el fondo de pantalla");
    }


    //3. Manejar los eventos de click sobre el context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.rojo:
                ventana.setBackgroundColor(Color.RED);
                break;
            case R.id.verde:
                ventana.setBackgroundColor(Color.GREEN);
                break;
            case R.id.azul:
                ventana.setBackgroundColor(Color.BLUE);
                break;
            case R.id.amarillo:
                ventana.setBackgroundColor(Color.YELLOW);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
