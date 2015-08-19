package com.androidatc.dailyfortune;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidatc.dailyfortune.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class FortuneActivity extends ActionBarActivity {

    private MyPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        pref = new MyPreferences(FortuneActivity.this);

        if(pref.isFirstTime()) {
            Toast.makeText(FortuneActivity.this, "Hi " + pref.getUsername(), Toast.LENGTH_LONG).show();
            pref.setOld(true);
        }
        else {
            Toast.makeText(FortuneActivity.this, "Welcome back "+pref.getUsername(), Toast.LENGTH_LONG).show();
        }

        ConnectionDetector cd = new ConnectionDetector(this);
        if(cd.isConnectingToInternet()){
            getFortuneOnline();
        } else {
            readfromFile();
        }
    }

    private void getFortuneOnline(){
        //Set the fortune text to loading
        final TextView fortune = (TextView) findViewById(R.id.fortune);
        fortune.setText("Loading....");

        final TextView autorTV = (TextView) findViewById(R.id.autor);

        // Creamos una instancia de la solicitud
        // Para obtener las frases vemos los msjs de esta url:
        // https://www.mashape.com/andruxnet/random-famous-quotes
        JsonObjectRequest Request = new JsonObjectRequest(com.android.volley.Request.Method.GET,
                "https://andruxnet-random-famous-quotes.p.mashape.com/cat=movies",
                (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Respuesta", response.toString());
                        String fortuna;
                        String autor;
                        //Parseamos la frase
                        try{
                            fortuna = response.getString("quote");
                            autor = response.getString("author");

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: "+ e.getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            fortuna = "Error";
                            autor = "Error";
                        }

                        fortune.setText(fortuna);
                        autorTV.setText(autor);
                        writeToFile(fortuna + " from " + autor);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respuesta", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error: "+ error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("X-Mashape-Key", "XtsDCtcJMKmshrmKNZ6sPd6yqBxNp1tM0OnjsnR5JgSU2j1fzl");
                            headers.put("Content-Type", "application/x-www-form-urlencoded");
                            headers.put("Accept", "application/json");
                            return headers;
                        }
                    };

        //Añadimos la solicitud a la cola de solicitudes
        AppController.getInstance().addToRequestQueue(Request);
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("Fortune.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Message:" , "File write failed:"+ e.toString());
        }

        Log.d("Message: ", "Paso... write to file successful");
    }

    private String readfromFile() {
        String ret = null;

        try {
            InputStream is = openFileInput("Fortune.json");

            if(is != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader b = new BufferedReader(inputStreamReader);
                String receivedString = "";
                StringBuilder stringBuilder = new StringBuilder();
                Log.v("Message: ", "reading:...");
                while ((receivedString = b.readLine()) != null) {
                    stringBuilder.append(receivedString);
                }

                is.close();
                ret = stringBuilder.toString();
            }

        } catch (FileNotFoundException e) {
            Log.e("Message: ", " File not found: "+e.toString());
        } catch (IOException e) {
            Log.e("Message: ", " Can not read file: "+e.toString());
        }

        return ret;
    }


}
