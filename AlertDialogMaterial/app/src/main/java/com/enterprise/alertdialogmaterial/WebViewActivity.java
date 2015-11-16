package com.enterprise.alertdialogmaterial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener{

    Button abc;
    Button ultimaHora;
    Button laNacion;

    WebView diario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        abc = (Button) findViewById(R.id.abc);
        ultimaHora = (Button) findViewById(R.id.ultima_hora);
        laNacion = (Button) findViewById(R.id.la_nacion);

        abc.setOnClickListener(this);
        ultimaHora.setOnClickListener(this);
        laNacion.setOnClickListener(this);

        diario = (WebView) findViewById(R.id.webView);

        //Si abrimos una página Web en una vista WebView y hacemos clic sobre alguno de sus enlaces,
        //éste se abrirá en el navegador Web de nuestro dispositivo en vez de abrir la página en el WebView.
        //Este comportamiento puede sobrescribirse, permitiendo al usuario moverse hacia adelante
        //y atrás a través del historial almacenado en el WebView.
        //Para sobrescribir dicho comportamiento debemos de definir un WebViewClient
        diario.setWebViewClient(new WebViewClient());

        //Por defecto, JavaScript esta deshabilitado en un WebView.
        //Para habilitarlo debemos de modificar el WebSettings de nuestro WebView.
        diario.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.abc:
                diario.loadUrl("http:www.abc.com.py");
                break;
            case R.id.ultima_hora:
                diario.loadUrl("http:www.ultimahora.com");
                break;
            case R.id.la_nacion:
                diario.loadUrl("www.lanacion.com.py");
                break;
        }
    }

    //Vamos a sobrescribir el método onBackPressed() de nuestra actividad principal para que al
    //pulsar el botón Atrás de nuestro dispositivo volvamos a la página anterior en el WebView:

    @Override
    public void onBackPressed() {
        //Primero debemos comprobar si existe un historial de navegacion
        if(diario.canGoBack()){
            diario.goBack();
        } else{
            super.onBackPressed();
        }

    }
}
