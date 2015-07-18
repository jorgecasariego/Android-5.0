package com.androidatc.androidui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mAlertDialog;
    Button myDialog;
    Button ImDialogActivity;
    Button mProgressBar;
    Button mTimePicker;
    Button mDatePicker;
    Button mCircularProgress;
    Button mContextMenu;
    Button mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAlertDialog = (Button) findViewById(R.id.alertDialog);
        mAlertDialog.setOnClickListener(this);

        myDialog = (Button) findViewById(R.id.myDialog);
        myDialog.setOnClickListener(this);

        ImDialogActivity = (Button) findViewById(R.id.imDialogActivity);
        ImDialogActivity.setOnClickListener(this);

        mProgressBar = (Button) findViewById(R.id.progressBar);
        mProgressBar.setOnClickListener(this);

        mTimePicker = (Button) findViewById(R.id.timePicker);
        mTimePicker.setOnClickListener(this);

        mDatePicker = (Button) findViewById(R.id.datePicker);
        mDatePicker.setOnClickListener(this);

        mCircularProgress = (Button) findViewById(R.id.circularProgress);
        mCircularProgress.setOnClickListener(this);

        mContextMenu = (Button) findViewById(R.id.contextMenu);
        mContextMenu.setOnClickListener(this);

        mWebView = (Button) findViewById(R.id.webView);
        mWebView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()){
            case R.id.alertDialog:
                crearAlertDialog();
                break;
            case R.id.myDialog:
                crearMyDialog();
                break;
            case R.id.imDialogActivity:
                i = new Intent(this, ImDialogActivity.class);
                startActivity(i);
                break;
            case R.id.progressBar:
                i = new Intent(this, ProgressBarActivity.class);
                startActivity(i);
                break;
            case R.id.timePicker:
                i = new Intent(this, TimePickerActivity.class);
                startActivity(i);
                break;
            case R.id.datePicker:
                i = new Intent(this, DatePickerActivity.class);
                startActivity(i);
                break;
            case R.id.circularProgress:
                i = new Intent(this, CircularProgressActivity.class);
                startActivity(i);
                break;
            case R.id.contextMenu:
                i = new Intent(this, ContextMenuActivity.class);
                startActivity(i);
                break;
            case R.id.webView:
                i = new Intent(this, WebViewActivity.class);
                startActivity(i);
                break;
        }
    }

    private void crearAlertDialog() {
        //1. Creamos un objeto de la clase AlertDialog a través de la clase Builder:
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //2. Creamos el mensaje del dialog
        alertDialogBuilder.setMessage("¿Desea borrar los datos?");

        alertDialogBuilder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Has borrado todos los datos del telefono con exito", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Accion cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        //2. Creamos un Dialog a partir de la alerta contruida
        AlertDialog alerta = alertDialogBuilder.create();
        alerta.show();

    }

    private void crearMyDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //1 Crear un menu desde el codigo java
        MenuItem menu1 = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Preferencias");
        //MenuItem menu2 = menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Salir");

        //1.1 Si queremos agregar un icono al menu
        menu1.setIcon(R.drawable.abc_ic_search_api_mtrl_alpha);

        //2. Crear un menu desde codigo xml
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //3. Agregando submenus desde java
        SubMenu submenu = menu.addSubMenu(0, Menu.NONE, 3, "Nuevo");
        submenu.add(Menu.NONE, 100, 1, "Nuevo").setCheckable(true);
        submenu.add(Menu.NONE, 101, 2, "Viejo").setCheckable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case Menu.FIRST:
                Toast.makeText(this, "Preferencias", Toast.LENGTH_SHORT).show();
                return true;
            case Menu.FIRST + 1:
                finish();
                return true;
            case R.id.action_preferencias:
                Toast.makeText(this, "Preferencias", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_salir:
                finish();
                return true;
            case 100:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
            default:
                return true;
        }
    }

    public void showPopupMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_context_menu, popupMenu.getMenu());
        popupMenu.show();


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this,"Has elegido el color : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}
