package com.enterprise.fragmentburgerking;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button combo1;
    private Button combo2;
    private Button combo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        combo1 = (Button) findViewById(R.id.combo1);
        combo2 = (Button) findViewById(R.id.combo2);
        combo3 = (Button) findViewById(R.id.combo3);

        combo1.setOnClickListener(this);
        combo2.setOnClickListener(this);
        combo3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch (v.getId()){
            case R.id.combo1:
                fragment = new Combo1();
                break;
            case R.id.combo2:
                fragment = new Combo2();
                break;
            case R.id.combo3:
                fragment = new Combo3();
                break;
        }

        if(fragment != null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(R.id.contenedor, fragment, "");
            transaction.commit();
        }
    }
}
