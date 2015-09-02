package com.androidatc.materialdesign;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.androidatc.materialdesign.loggin.L;
import com.androidatc.materialdesign.network.VolleySingleton;

import org.json.JSONObject;

/**
 * Created by jorgecasariego on 1/9/15.
 */
public class FragmentBoxOffice extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String URL_BOX_OFFICE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";

    private String param1;
    private String param2;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;


    public static FragmentBoxOffice newInstance(String param1, String param2){
        FragmentBoxOffice fragmentSearch = new FragmentBoxOffice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragmentSearch.setArguments(args);

        return fragmentSearch;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_box_office, container, false);

        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            param1 = getArguments().getString(ARG_PARAM1);
            param2 = getArguments().getString(ARG_PARAM2);
        }

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), (String)null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                L.t(getActivity(), response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }

    public static String getRequestUrl(int limit){
        return URL_BOX_OFFICE + "?apikey="+ MyApplication.API_KEY_ROTTEN_TOMATOES +"&limit="+limit;
    }
}
