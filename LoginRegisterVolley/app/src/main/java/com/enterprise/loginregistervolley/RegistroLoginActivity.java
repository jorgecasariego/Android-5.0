package com.enterprise.loginregistervolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroLoginActivity extends AppCompatActivity implements View.OnClickListener{

    //URL del servicio
    public static final String REGISTER_URL = "http://android.enterprisesolutions.com.py/volleyRegister.php";

    //Claves que espera nuestro servidor
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";

    // 1. Definición de vistas
    private EditText usuarioView;
    private EditText emailView;
    private EditText passwordView;

    private Button registrarView;
    private Button loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);

        // 2. Asociar vistas con Ids del layout
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
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    private void registrarUsuario() {
        final String usuario = usuarioView.getText().toString().trim();
        final String password = passwordView.getText().toString().trim();
        final String email = emailView.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegistroLoginActivity.this, "Respuesta: " + response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistroLoginActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() {
                //Parametros a enviar al servidor
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put(KEY_USERNAME, usuario);
                parametros.put(KEY_EMAIL, email);
                parametros.put(KEY_PASSWORD, password);

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
