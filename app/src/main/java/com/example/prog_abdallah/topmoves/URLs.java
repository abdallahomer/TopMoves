package com.example.prog_abdallah.topmoves;

import android.util.Log;

/**
 * Created by ProG_AbdALlAh on 1/7/2017.
 */

public class URLs {
    public static final String BASE_URL = "https://api.trakt.tv/";
    public static final String BASE_URL_1 = "http://www.omdbapi.com/?";
    public static final String CLIENT_ID = "721384d35fa749f453b029fac337f725566c5dbae889d1a756cb1c9752d259b7";
    public static final String YOUTUBE_ANDROID_PLAYER_API = "AIzaSyAGUCMusNHmgSA7lH7oY_PTrfCVcRGWUyY";
    public static final String LOG_TAG = URLs.class.getName();

    public static String getTopMoviesURL(String category,int page){
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL)
                .append("movies")
                .append("/")
                .append(category)
                .append("?page="+page)
                .append("&extended=full");
        Log.i(LOG_TAG, "Get URL of API");
        return url.toString();
    }

    public static String getSearchURL(String keyword,int page){
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL_1)
                .append("s=")
                .append(keyword)
                .append("&type=movie")
                .append("&page="+page);
       // http:www.omdbapi.com/?s=iron&type=movie&page=3
        return url.toString();
    }

}
