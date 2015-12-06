package com.enterprise.loginregistervolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    // 7. Ingresar la URL del servicio de Login

    // 8. Ingresar las claves que el servidor reconoce para obtener datos del usuario

    // Vistas
    private EditText usuarioView;
    private EditText passwordView;
    private Button loginView;

    private String usuario;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioView = (EditText) findViewById(R.id.editTextUsername);
        passwordView = (EditText) findViewById(R.id.editTextPassword);

        loginView = (Button) findViewById(R.id.buttonLogin);

        loginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SolicitarInicioSesion(v);
    }

    private void SolicitarInicioSesion(View v) {
        // Obtenemos los datos ingresados por el usuario
        usuario = usuarioView.getText().toString().trim();
        password = passwordView.getText().toString().trim();

        // 9. Crear un objeto Request pasando los datos del usuario

        // 10. Añadir a cola de solicitudes
    }

    private void openProfile(){

        // 11. Iniciar nueva actividad y pasar los datos del usuario
    }
}

