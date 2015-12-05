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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String LOGIN_URL = "http://android.enterprisesolutions.com.py/login.php";

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";

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
        usuario = usuarioView.getText().toString().trim();
        password = passwordView.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            //Abrimos una nueva actividad dando la Bienvenida
                            openProfile();
                        } else {
                            //Mostramos el error
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put(KEY_USERNAME, usuario);
                parametros.put(KEY_PASSWORD, password);

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(KEY_USERNAME, usuario);
        startActivity(intent);
    }
}

