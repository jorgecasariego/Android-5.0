package com.androidatc.rotatescreenexample;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText valor;
    String valorObtenido;
    static final String CLAVE_VALOR = "clave valor";

    /**
     * Cuando la actividad es recreada despues que ha sido destruida, es posible recuperar
     * el estado en que se encontraba a traves del Bundle que el Sistema pasa a nuestra actividad.
     *
     * Ya sea el metodo onCreate() y onRestoreInstanceState() reciben el mismo Bundle que contiene
     * la información del estado de la instancia
     *
     * Ya que el metodo onCreate() es llamado tanto si el sistema esta creando una nueva instancia
     * de la actividad o recreando una actividad previa, es necesario chequear si el estado del
     * Bundle es null antes de poder leerlo. Si es null significa que el Sistema esta creando una
     * nueva instancia de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor = (EditText) findViewById(R.id.valorEditText);
        /*
            //Primero chequeamos si estamos recreando una instancia previamente destruida
            if(savedInstanceState != null){
                //Restauramos el valor de los miembros del estado guardado
                valorObtenido = savedInstanceState.getString(CLAVE_VALOR);
            } else{
                //Probablemente inicializariamos los miembros con valores por default para una nueva instancia
            }
        */

    }

/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        valorObtenido = valor.getText().toString();
        outState.putString(CLAVE_VALOR, valorObtenido);

        super.onSaveInstanceState(outState);
    }
*/

    /**
     * En ves de restaurar el estado durante la llamada al metodo onCreate() podriamos elegir implementar
     * onRestoreInstanceState(), el cual el sistema llama despues del metodo onStart(). El sistema llama
     * onRestoreInstanceState() solo en el caso de que haya un estado guardado, asi que en este caso
     * no va a ser necesario chequear si el Bundle es null
     */
    /*
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restauramos el valor de los miembros del estado guardado
        valorObtenido = savedInstanceState.getString(CLAVE_VALOR);
    }*/


    /**
     * El objeto Configuration representa toda la configuracion actual, no solo los datos que han sido
     * cambiados.
     *
     * Tener en cuenta que cuando declaramos nuestra actividad para que maneje los cambios
     * en la configuracion nosotros somos los responsables de resetear cualquier elemento
     * para la cual proveemos alternativas. Por ejemplo en el caso de imagenes que podrian
     * cambiar en tamaño y forma dependiendo de la orientacion (Landscape o portrait)
     *
     * Si no necesitamos actualizar nuestra aplicación basado en los cambios en la configuracion,
     * se podría no querer implementar este metodo. En tal caso se usaria siempre la misma configuración
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //Chequeamos la orientacion de la pantalla
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_LONG).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
