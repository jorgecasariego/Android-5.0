package com.enterprise.loginregistervolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroLoginActivity extends AppCompatActivity implements View.OnClickListener{

    // 3. Ingresar URL del servicio de registro de usuario


    //4. Ingresar las claves que espera nuestro servidor


    // Definición de vistas
    private EditText usuarioView;
    private EditText emailView;
    private EditText passwordView;

    private Button registrarView;
    private Button loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);

        // Asociar vistas con Ids del layout
        usuarioView = (EditText) findViewById(R.id.editTextUsername);
        passwordView = (EditText) findViewById(R.id.editTextPassword);
        emailView= (EditText) findViewById(R.id.editTextEmail);

        registrarView = (Button) findViewById(R.id.buttonRegister);
        registrarView.setOnClickListener(this);

        loginView = (Button) findViewById(R.id.buttonLogin);
        loginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonRegister:
                registrarUsuario();
                break;
            case R.id.buttonLogin:
                // 12. Iniciar nueva actividad
                break;
        }
    }

    private void registrarUsuario() {
        final String usuario = usuarioView.getText().toString().trim();
        final String password = passwordView.getText().toString().trim();
        final String email = emailView.getText().toString().trim();

        // 5. Crear un objeto del tipo StringRequest

        // 6. Añadir la solicitud a la cola
    }
}
