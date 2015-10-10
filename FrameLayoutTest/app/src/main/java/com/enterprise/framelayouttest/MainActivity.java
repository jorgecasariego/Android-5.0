package com.enterprise.framelayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * 1. Copiar 2 imagenes en la carpeta drawable
 * 2. Creamos un FrameLayout
 * 3. Crear 2 ImagesViews dentro del FrameLayout
 * 4. Hacemos el segundo ImageView invisible
 *
 * Funcionamiento: Lo que queremos es que cuando pulsemos sobre una imagen
 * la imagen 1 desaparezca y la imagen 2 aparezca
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imagen1View;
    private ImageView imagen2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen1View = (ImageView) findViewById(R.id.imagen1);
        imagen2View = (ImageView) findViewById(R.id.imagen2);

        imagen1View.setOnClickListener(this);
        imagen2View.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagen1:
                imagen1View.setVisibility(View.GONE);
                imagen2View.setVisibility(View.VISIBLE);
                break;
            case R.id.imagen2:
                imagen2View.setVisibility(View.GONE);
                imagen1View.setVisibility(View.VISIBLE);
                break;
        }
    }
}
