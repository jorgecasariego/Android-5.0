package com.androidatc.imageswitcherexample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


/**
 * Los metodos TextSwitcher y ImageSwitcher nos ofrecen una manera simple de animar transiciones.
 *
 * TextSwitcher and ImageSwitcher estan disponibles desde Android v1.6.
 * TextSwitcher reemplaza al TextView y ImageSwitcher reemplaza al ImageView
 *
 * TextSwitcher y el TextView funcionan de la misma manera.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String[] TEXTOS = {
            "Imagen 1",
            "Imagen 2",
            "Imagen 3"
    };

    private static final int[] IMAGENES = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3
    };

    private int mPosicion = 0;

    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;
    private Button mNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);

        //TextSwitcher usa Factory para crear nuevas vistas, y donde sea que usemos setText(),
        //lo primero que hace es remover la vista vieja usando una animación seteada con el
        //metodo setOutAnimation, y luego coloca la nueva vista usando la animacion setInAnimation
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);

                return textView;
            }
        });

        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);


        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                return imageView;
            }
        });

        //Asi como funcionan los efectos de fade_in y fade_out podemos usar cualquiera que queramos
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        onSwitch();
        mNext = (Button) findViewById(R.id.next);
        mNext.setOnClickListener(this);

    }

    private void onSwitch() {
        mTextSwitcher.setText(TEXTOS[mPosicion]);
        mImageSwitcher.setBackgroundResource(IMAGENES[mPosicion]);
        mPosicion = (mPosicion + 1) % TEXTOS.length;
    }

    @Override
    public void onClick(View v) {
        onSwitch();
    }
}
