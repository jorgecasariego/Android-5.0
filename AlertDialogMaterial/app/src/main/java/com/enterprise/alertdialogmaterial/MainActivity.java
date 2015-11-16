package com.enterprise.alertdialogmaterial;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        mContextMenu = (Button) findViewById(R.id.contextMenu);
        mContextMenu.setOnClickListener(this);

        mWebView = (Button) findViewById(R.id.webView);
        mWebView.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Para agregar un menu desde Java creamos un MenuItem y luego agregamos al menu
        // (groupId, itemId, order, titleRes)
        MenuItem item = menu.add(Menu.NONE, Menu.FIRST, 99, "Mi perfil");

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_logout){
            finish();
        } else if(id == Menu.FIRST){
            Toast.makeText(MainActivity.this, "Ir a mi perfil", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i;
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
                i = new Intent(this, ProgressBar.class);
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
                Toast.makeText(MainActivity.this, "Has borrado todos los datos del telefono con exito!", Toast.LENGTH_SHORT).show();

            }
        });

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Accion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        //2. Creamos un Dialog a partir de la alerta contruida
        AlertDialog alerta = alertDialogBuilder.create();
        alerta.show();
    }

    private void crearMyDialog() {
        Button yes;
        Button no;

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);


        yes = (Button) dialog.findViewById(R.id.positiveButton);
        no = (Button) dialog.findViewById(R.id.negativeButton);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yes!!", Toast.LENGTH_SHORT).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "NO :(", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
