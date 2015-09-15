package com.androidatc.materialdesign.json;

import com.androidatc.materialdesign.MyApplication;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_BOX_OFFICE;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_CHAR_AMEPERSAND;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_CHAR_QUESTION;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_PARAM_API_KEY;
import static com.androidatc.materialdesign.extras.UrlEndPoints.URL_PARAM_LIMIT;

/**
 * Created by jorgecasariego on 10/9/15.
 */
public class Endpoints {
    public static String getRequestUrlBoxOfficeMovies(int limit) {

        return URL_BOX_OFFICE
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY + MyApplication.API_KEY_ROTTEN_TOMATOES
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_LIMIT + limit;
    }


    public static String getRequestUrlUpcomingMovies(int limit) {

        /*return URL_UPCOMING
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY + MyApplication.API_KEY_ROTTEN_TOMATOES
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_LIMIT + limit;*/
        return "";
    }

}
