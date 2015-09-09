package com.androidatc.materialdesign.extras;

import com.androidatc.materialdesign.pojo.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by jorgecasariego on 8/9/15.
 */
public class MovieSorter {
    public void sortMovieByName(ArrayList<Movie> movies){
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());
            }
        });
    }

    public void sortMoviesByDate(ArrayList<Movie> movies){
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return lhs.getReleaseDateTheater().compareTo(rhs.getReleaseDateTheater());
            }
        });
    }

    public void sortMoviesByRating(ArrayList<Movie> movies){
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                int ratingLhs = lhs.getAudienceScore();
                int ratingRhs = rhs.getAudienceScore();

                if(ratingLhs < ratingRhs){
                    return 1;
                } else if (ratingLhs > ratingRhs){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
}
