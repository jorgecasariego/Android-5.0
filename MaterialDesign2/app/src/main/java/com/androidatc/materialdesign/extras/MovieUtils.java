package com.androidatc.materialdesign.extras;

import com.android.volley.RequestQueue;
import com.androidatc.materialdesign.MyApplication;
import com.androidatc.materialdesign.database.DBMovies;
import com.androidatc.materialdesign.json.Endpoints;
import com.androidatc.materialdesign.json.Parser;
import com.androidatc.materialdesign.json.Requestor;
import com.androidatc.materialdesign.pojo.Movie;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jorgecasariego on 10/9/15.
 */
public class MovieUtils {
    public static ArrayList<Movie> loadBoxOfficeMovies(RequestQueue requestQueue) {
        //Obtenemos el json con todas las peliculas
        JSONObject response = Requestor.requestMoviesJSON(requestQueue, Endpoints.getRequestUrlBoxOfficeMovies(30));

        //Almacenamos la respuesta en un arrayList de Movies
        ArrayList<Movie> listMovies = Parser.parseMoviesJSON(response);

        //Insertamos el arraylist de peliculas en la base de datos
        MyApplication.getWritableDatabse().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
        return listMovies;
    }

    public static ArrayList<Movie> loadUpcomingMovies(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestMoviesJSON(requestQueue, Endpoints.getRequestUrlUpcomingMovies(30));
        ArrayList<Movie> listMovies = Parser.parseMoviesJSON(response);
        MyApplication.getWritableDatabse().insertMovies(DBMovies.UPCOMING, listMovies, true);
        return listMovies;
    }
}
