package com.androidatc.androidui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    Button abc, ultimahora, lanacion;
    WebView webDiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        abc = (Button) findViewById(R.id.abc);
        abc.setOnClickListener(this);

        ultimahora = (Button) findViewById(R.id.ultimahora);
        ultimahora.setOnClickListener(this);

        lanacion = (Button) findViewById(R.id.laNacion);
        lanacion.setOnClickListener(this);

        webDiario = (WebView) findViewById(R.id.webViewDiarios);

        //Si abrimos una página Web en una vista WebView y hacemos clic sobre alguno de sus enlaces,
        //éste se abrirá en el navegador Web de nuestro dispositivo en vez de abrir la página en el WebView.
        //Este comportamiento puede sobrescribirse, permitiendo al usuario moverse hacia adelante
        //y atrás a través del historial almacenado en el WebView.
        //Para sobrescribir dicho comportamiento debemos de definir un WebViewClient
        webDiario.setWebViewClient(new WebViewClient());

        //Por defecto, JavaScript esta deshabilitado en un WebView.
        //Para habilitarlo debemos de modificar el WebSettings de nuestro WebView.
        webDiario.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.abc:
                webDiario.loadUrl("http://www.abc.com.py");
                break;
            case R.id.ultimahora:
                webDiario.loadUrl("http://www.ultimahora.com");
                break;
            case R.id.laNacion:
                webDiario.loadUrl("http://www.lanacion.com.py");
                break;
        }
    }

    //Vamos a sobrescribir el método onBackPressed() de nuestra actividad principal para que al
    //pulsar el botón Atrás de nuestro dispositivo volvamos a la página anterior en el WebView:
    @Override
    public void onBackPressed() {
        //Primero debemos comprobar si existe un historial de navegacion
        if(this.webDiario.canGoBack()){
            this.webDiario.goBack();
        } else{
            super.onBackPressed();
        }

    }
}
