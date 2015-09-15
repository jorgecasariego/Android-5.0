package com.androidatc.materialdesign.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.androidatc.materialdesign.MyApplication;
import com.androidatc.materialdesign.callbacks.BoxOfficeMoviesLoadedListener;
import com.androidatc.materialdesign.database.DBMovies;
import com.androidatc.materialdesign.extras.MovieUtils;
import com.androidatc.materialdesign.network.VolleySingleton;
import com.androidatc.materialdesign.pojo.Movie;

import org.json.JSONObject;

import java.util.ArrayList;

import me.tatarka.support.job.JobParameters;

/**
 * Created by jorgecasariego on 10/9/15.
 */
public class TaskLoadMoviesBoxOffice extends AsyncTask<Void, Void, ArrayList<Movie>> {
    private BoxOfficeMoviesLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadMoviesBoxOffice(BoxOfficeMoviesLoadedListener myComponent) {
        this.myComponent = myComponent;
        this.volleySingleton = VolleySingleton.getInstance();
        this.requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... params) {


        ArrayList<Movie> listMovies = MovieUtils.loadBoxOfficeMovies(requestQueue);

        return listMovies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> listMovies) {
        if(myComponent != null){
            myComponent.onBoxOfficeMoviesLoaded(listMovies);
        }
    }
}
