package com.androidatc.explicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        //Obtenemos el intent, action y MIME
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(action != null && action.compareTo(Intent.ACTION_VIEW) == 0){
            //Obtenemos los datos
            Uri data = intent.getData();

            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
            Log.v("Log: action", action);
            Log.v("Log: view", intent.ACTION_VIEW);
        }

    }
}
