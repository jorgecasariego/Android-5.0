package com.enterprise.loginregistervolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textView = (TextView) findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        // 13. Obtenemos el nombre del usuario logueado
        // 14. Mostramos en el textView
    }
}
