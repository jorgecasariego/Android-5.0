package com.example.jorgecasariego.testfoursquare;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    ArrayList<FoursquareVenue> venuesList;

    // the foursquare client_id and the client_secret

    // ============== YOU SHOULD MAKE NEW KEYS ====================//
    final String CLIENT_ID = "MNWPOOKORV5NOPAWK3WWERXUH5SO1XIKTUQAQCDXAOGERVGP";
    final String CLIENT_SECRET = "HAO2P0AAEA5ORDOYZOCJGLTRNR243DUN4ORYTRR5GAMZGL4M";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the center of New York
    final String latitude = "-25.2854300";
    final String longtitude = "-57.6410570";

    ArrayAdapter<String> myAdapter;
    TextView lugarElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lugarElegido = (TextView) findViewById(R.id.lugarElegido);

        getPlaces();
    }

    private void getPlaces(){
        String apiCall = "https://api.foursquare.com/v2/venues/search?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&v=20130815%20&ll="+latitude+","+longtitude;
        JsonObjectRequest Request = new JsonObjectRequest(com.android.volley.Request.Method.GET,
                apiCall,
                (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Respuesta", response.toString());


                        //Parseamos la frase
                        venuesList = (ArrayList<FoursquareVenue>) parseFoursquare(response);

                        List<String> listTitle = new ArrayList<String>();

                        for (int i = 0; i < venuesList.size(); i++) {
                            listTitle.add(i, venuesList.get(i).getName() + ", " + venuesList.get(i).getCategory() + "" + venuesList.get(i).getCity());
                        }

                        // set the results to the list
                        // and show them in the xml
                        myAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.row_layout, R.id.listText, listTitle);
                        setListAdapter(myAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Respuesta", "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                "Error: "+ error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        //Añadimos la solicitud a la cola de solicitudes
        AppController.getInstance().addToRequestQueue(Request);
    }



    private static ArrayList<FoursquareVenue> parseFoursquare(final JSONObject jsonObject) {

        ArrayList<FoursquareVenue> temp = new ArrayList<FoursquareVenue>();
        try {

            // make an jsonObject in order to parse the response
            //JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("venues")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        FoursquareVenue poi = new FoursquareVenue();
                        if (jsonArray.getJSONObject(i).has("name")) {
                            poi.setName(jsonArray.getJSONObject(i).getString("name"));

                            if (jsonArray.getJSONObject(i).has("location")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("location").has("address")) {
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("city")) {
                                        poi.setCity(jsonArray.getJSONObject(i).getJSONObject("location").getString("city"));
                                    }
                                    if (jsonArray.getJSONObject(i).has("categories")) {
                                        if (jsonArray.getJSONObject(i).getJSONArray("categories").length() > 0) {
                                            if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                                                poi.setCategory(jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name"));
                                            }
                                        }
                                    }
                                    temp.add(poi);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<FoursquareVenue>();
        }
        return temp;

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        lugarElegido.setText("Lugar elegido: " + venuesList.get(position).getName().toString());
    }
}