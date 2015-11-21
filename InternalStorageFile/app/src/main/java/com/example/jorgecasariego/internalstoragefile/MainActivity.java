package com.example.jorgecasariego.internalstoragefile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String MI_ARCHIVO_DE_URLS = "mis_urls.txt";
    private static final int READ_BLOCK_SIZE = 100;

    private EditText url;
    private Button guardar;
    private Button leer;
    private Button leerAssets;
    private TextView misUrlGuardadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = (EditText) findViewById(R.id.url);

        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(this);

        leer = (Button) findViewById(R.id.leer);
        leer.setOnClickListener(this);

        leerAssets = (Button) findViewById(R.id.leer_asset);
        leerAssets.setOnClickListener(this);

        misUrlGuardadas = (TextView) findViewById(R.id.mis_url_guardadas);
        
        //Para saber en que carpeta esta guardada hacer
        Log.e("Ruta Archivos", "Guardado en: " + getFilesDir());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guardar:
                guardar_url_a_archivo();
                break;
            case R.id.leer:
                leer_url_desde_archivo();
                break;
            case R.id.leer_asset:
                leer_url_desde_assets();
                break;
        }
    }



    private void guardar_url_a_archivo() {
        try {
            // Creamos un nuevo output file stream
            FileOutputStream fileout = openFileOutput(MI_ARCHIVO_DE_URLS, Context.MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(url.getText().toString() + '\n');
            outputWriter.close();

            //Mostramos un mensaje diciendo que el archivo se guardo con exito
            Toast.makeText(getBaseContext(), "Url guardada exitosamente en archivo!", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void leer_url_desde_archivo() {
        try {
            FileInputStream fileIn = openFileInput(MI_ARCHIVO_DE_URLS);
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();

            //Mostramos las urls guardadas en el archivo
            misUrlGuardadas.setText(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leer_url_desde_assets() {
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("test.txt"));

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = inputStreamReader.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            inputStreamReader.close();

            //Mostramos las urls guardadas en el archivo
            misUrlGuardadas.setText(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
