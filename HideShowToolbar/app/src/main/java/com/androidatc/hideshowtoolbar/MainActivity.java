package com.androidatc.hideshowtoolbar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button marcacionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        marcacionButton = (Button) findViewById(R.id.marcacionButton);

        marcacionButton.setOnClickListener(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
    }

    @Override
    public void onClick(View v) {
        startActivity(MarcacionActivity.class);
    }

    private void startActivity(Class<?> activityClass) {
        Intent myIntent = new Intent(this, activityClass);
        startActivity(myIntent);
    }
}
