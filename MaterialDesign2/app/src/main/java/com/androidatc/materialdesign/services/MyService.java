package com.androidatc.materialdesign.services;

import android.os.AsyncTask;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.androidatc.materialdesign.MyApplication;
import com.androidatc.materialdesign.R;
import com.androidatc.materialdesign.callbacks.BoxOfficeMoviesLoadedListener;
import com.androidatc.materialdesign.database.DBMovies;
import com.androidatc.materialdesign.extras.Constants;
import com.androidatc.materialdesign.loggin.L;
import com.androidatc.materialdesign.network.VolleySingleton;
import com.androidatc.materialdesign.pojo.Movie;
import com.androidatc.materialdesign.task.TaskLoadMoviesBoxOffice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_AUDIENCE_SCORE;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_ID;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_MOVIES;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_POSTERS;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_RATINGS;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_RELEASE_DATES;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_SYNOPSIS;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_THEATER;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_THUMBNAIL;
import static com.androidatc.materialdesign.extras.Keys.EndpointBoxOffice.KEY_TITLE;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_BOX_OFFICE;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_CHAR_AMEPERSAND;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_CHAR_QUESTION;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_PARAM_API_KEY;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_PARAM_LIMIT;

/**
 * Created by jorgecasariego on 8/9/15.
 */
public class MyService extends JobService implements BoxOfficeMoviesLoadedListener {

    private JobParameters jobParameters;

    //onStartJob correo en el main Threat
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        L.t(this, "onStartJob");

        this.jobParameters = jobParameters;

        //new MyTask(this).execute(jobParameters);
        new TaskLoadMoviesBoxOffice(this).execute();

        //Si estamos ejecutando un hilo(en este caso un AsyncTask) necesitamos retornar true en
        //este metodo para indicar que se esta ejecutando algo en background sino simplemente false
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        L.t(this, "onStopJob");
        return false;
    }

    // Aqui llega cuando los datos han sido obtenidos desde Rotten Tomatoes y cargados en la BD
    @Override
    public void onBoxOfficeMoviesLoaded(ArrayList<Movie> listMovies) {
        L.t(this, "onBoxOfficeMoviesLoaded");
        jobFinished(jobParameters, false);
    }

    /*
    // Vamos a crear un AsyncTask para para hacer dos tareas:
    // 1. Parse and load data from Json
    // 2. Escribir en una base de datos los datos obtenidos desde aqui
    // Obs: al ser una variable estatica la de la clase no podemos acceder a variables fuera de esta clase
    private static class MyTask extends AsyncTask<JobParameters, Void, JobParameters> {
        private VolleySingleton volleySingleton;
        private RequestQueue requestQueue;

        private MyService myService;

        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        public MyTask(MyService myService) {
            this.myService = myService;
            this.volleySingleton = VolleySingleton.getInstance();
            this.requestQueue = volleySingleton.getRequestQueue();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JobParameters doInBackground(JobParameters... params) {
            // 1. Lo primero que tenemos que hacer es cargar el json desde Rottem Tomatoes
            // 2. Y para hacer esto deberiamos llamar a sendJsonRequest
            //    pero aqui surge un problema: Se van a estar ejecutando dos hilos al mismo tiempo
            //    y el hilo que se ejecuta aqui no va a saber cuando termina su trabajo el hilo que
            //    se esta ejcutando en Volley
            //    Para solucionar este inconveniente vamos a llamar a: RequestFuture
            //    y esto es lo vamos a utilizar en sendJsonRequest()

            JSONObject response = sendJsonRequest();
            ArrayList<Movie> listMovies = parseJsonRequest(response);

            //Con true hacemos que siempre limpie la base de datos si hay datos nuevos
            MyApplication.getWritableDatabse().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
            return params[0];
        }

        //Una vez que se termina de ejecutar nuestro hilo vamos a avisar de esto llamando a JobFinished
        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            myService.jobFinished(jobParameters, false);
        }


        private JSONObject sendJsonRequest() {
            JSONObject response = null;

            // La explicación de porque usamos RequestFuture esta explicado en doInBackground()
            // Para entender un poco mejor veamos al "requestFuture" como la llave de un auto
            // y "JSONObject" el auto
            // para que funcione el request hay que remover el onResponse y el onErrorResponse
            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    getRequestUrl(30),
                    (String) null,
                    requestFuture,
                    requestFuture);


            **
             * Manera que haciamos anes sin el requestFuture
             *
             JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
             getRequestUrl(30),
             (String) null,
             new Response.Listener<JSONObject>() {
            @Override public void onResponse(JSONObject response) {
            //L.t(getActivity(), response.toString());
            //mTextError.setVisibility(View.GONE);
            //listMovies = parseJsonRequest(response);
            //adapterBoxOffice.setMovieList(listMovies);
            }
            }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
            // handleVolleyError(error);
            }
            });

             *

            requestQueue.add(request);

            // En esta parte vamos a obtener un JSONObject
            // En esta parte del codigo nuestra app va a esperar el json desde internet
            // y puede tardar varios segundos pero eso no es un problema para nosotros
            // ya que estamos usando un Background Thread
            try {
                //Al get le vamos a pasar el tiempo para indicar cuanto tiempo vamos a esperar
                response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                L.m(e+"");
            } catch (ExecutionException e) {
                L.m(e + "");
            } catch (TimeoutException e) {
                L.m(e + "");
            }

            return response;
        }


        private ArrayList<Movie> parseJsonRequest(JSONObject response) {
            ArrayList<Movie> listMovies = new ArrayList<>();

            if (response != null || response.length() > 0) {
                try {
                    //StringBuilder data = new StringBuilder();

                    //if (response.has(KEY_MOVIES)) {
                    JSONArray arrayMovies = response.getJSONArray(KEY_MOVIES);

                    for (int i = 0; i < arrayMovies.length(); i++) {
                        long id = -1;
                        String title = Constants.NA;
                        String releaseDate = Constants.NA;
                        int audienceScore = -1;
                        String synopsis = Constants.NA;
                        String urlThumbnail = Constants.NA;

                        JSONObject currentMovie = arrayMovies.getJSONObject(i);

                        //Obtenemos el id de la pelicula actual
                        if (contains(currentMovie, KEY_ID)) {
                            id = currentMovie.getLong(KEY_ID);
                        }

                        if (contains(currentMovie, KEY_TITLE)) {
                            title = currentMovie.getString(KEY_TITLE);
                        }

                        if (contains(currentMovie, KEY_RELEASE_DATES)) {
                            JSONObject objectReleaseDate = currentMovie.getJSONObject(KEY_RELEASE_DATES);

                            if (contains(objectReleaseDate, KEY_THEATER)) {
                                releaseDate = objectReleaseDate.getString(KEY_THEATER);
                            }
                        }


                        JSONObject objectRating = currentMovie.getJSONObject(KEY_RATINGS);

                        if (contains(objectRating, KEY_AUDIENCE_SCORE)) {

                            audienceScore = objectRating.getInt(KEY_AUDIENCE_SCORE);
                        }

                        if (contains(currentMovie, KEY_SYNOPSIS)) {
                            synopsis = currentMovie.getString(KEY_SYNOPSIS);
                        }

                        if (contains(currentMovie, KEY_POSTERS)) {
                            JSONObject objectPoster = currentMovie.getJSONObject(KEY_POSTERS);

                            if (contains(objectPoster, KEY_THUMBNAIL)) {
                                urlThumbnail = objectPoster.getString(KEY_THUMBNAIL);
                            }
                        }

                        Movie movie = new Movie();
                        movie.setId(id);
                        movie.setTitle(title);

                        Date date = null;
                        try {
                            date = dateFormat.parse(releaseDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        movie.setReleaseDateTheater(date);
                        movie.setAudienceScore(audienceScore);
                        movie.setSynopsis(synopsis);
                        movie.setUrlThumbnail(urlThumbnail);

                        //Aqui decidimos cuando cargar una pelicula a la lista de peliculas
                        if (id != -1 && !title.equals(Constants.NA)) {
                            listMovies.add(movie);
                        }


                    }

                    //L.t(getActivity(), listMovies.toString());
                    //}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return listMovies;
        }

        private boolean contains(JSONObject jsonObject, String key) {
            return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
        }

        public static String getRequestUrl(int limit) {
            return URL_BOX_OFFICE
                    + URL_CHAR_QUESTION
                    + URL_PARAM_API_KEY + MyApplication.API_KEY_ROTTEN_TOMATOES
                    + URL_CHAR_AMEPERSAND
                    + URL_PARAM_LIMIT
                    + limit;


        }
    }*/
}
