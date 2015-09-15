package com.androidatc.materialdesign.callbacks;

import com.androidatc.materialdesign.pojo.Movie;

import java.util.ArrayList;

/**
 * Created by jorgecasariego on 10/9/15.
 */
public interface BoxOfficeMoviesLoadedListener {
    public void onBoxOfficeMoviesLoaded(ArrayList<Movie> listMovies);
}
