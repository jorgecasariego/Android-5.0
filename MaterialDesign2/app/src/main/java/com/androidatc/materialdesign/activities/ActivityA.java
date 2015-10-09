package com.androidatc.materialdesign.activities;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.androidatc.materialdesign.R;

public class ActivityA extends AppCompatActivity implements View.OnClickListener{

    private ViewGroup mRoot;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.content_transition_a);
            getWindow().setExitTransition(transition);
            Slide slide = new Slide();
            slide.setDuration(5000);
            getWindow().setReenterTransition(slide);
        }


        setContentView(R.layout.activity_a);

        mRoot = (ViewGroup) findViewById(R.id.container_a);

        mButton1 = (Button) findViewById(R.id.button_1);
        mButton2 = (Button) findViewById(R.id.button_2);
        mButton3 = (Button) findViewById(R.id.button_3);
        mButton4 = (Button) findViewById(R.id.button_4);
        mRoot.setOnClickListener(this);
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {

        // 1. Opcion con Fade
        //Fade fade = new Fade();
        //fade.setDuration(1000);

        // 2. Opcion sin Fade
        //TransitionManager.beginDelayedTransition(mRoot);
        //toggleVisibility(mButton1, mButton2, mButton3, mButton4);

        // 3. Llamando a una nueva actividad
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        startActivity(new Intent(this, ActivityB.class), activityOptionsCompat.toBundle());
    }

    public void toggleVisibility(View... views){
        for(View current : views){

            //Con esta parte del codigo lo que hacemos es cambiar el tamaño de todas las vistas
            ViewGroup.LayoutParams params = current.getLayoutParams();
            params.height = 100;
            params.width = 50;
            current.setLayoutParams(params);

            //Con esto hacemos que todas las vistas pasen a desaparecer con una transitio suave
            // usando (Fade)
            //if(current.getVisibility() == View.VISIBLE){
            //    current.setVisibility(View.INVISIBLE);
            //}
        }
    }
}
