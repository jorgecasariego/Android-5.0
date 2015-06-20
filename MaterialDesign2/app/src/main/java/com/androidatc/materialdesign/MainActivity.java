package com.androidatc.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Pasos para configurar ToolBar
 * 1. Usar Theme.AppCompat.Light.NoActionBar  para prevenir que el sistema muestre el action bar
 * 2. Definir app_bar.xml conteniendo un ToolBar
 * 3. Usar <include> en layout.xml para añadir el ToolBar
 * 4. Instanciar el toolbar usando findViewById dentro del Activity
 * 5. LLamar a setSupportActionBar y pasarle el objeto ToolBar
 * 6. Personalizar app:theme y app:popupTheme si es necesario
 * 7. Personzalizar las diferentes propiedades del ToolBar ya sea usando el objeto toolBar
 *    directamente o usando getSupportActionBar()
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        }

        return super.onOptionsItemSelected(item);
    }
}
