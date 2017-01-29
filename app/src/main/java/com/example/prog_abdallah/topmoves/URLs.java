package com.example.prog_abdallah.topmoves;

import android.util.Log;

/**
 * Created by ProG_AbdALlAh on 1/7/2017.
 */

public class URLs {
    public static final String BASE_URL = "https://api.trakt.tv/";
    public static final String BASE_URL_1 = "https://api.themoviedb.org/3/";
    public static final String POSTER_URL = "https://api.themoviedb.org/3/movie/";
    public static final String CLIENT_ID = "721384d35fa749f453b029fac337f725566c5dbae889d1a756cb1c9752d259b7";
    public static final String TMDB_API_KEY = "17e8cdf2666c1b20ef3323dc00c6e0e1";
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
                .append("search/")
                .append("movie?")
                .append("api_key=")
                .append(TMDB_API_KEY)
                .append("&query=")
                .append(keyword)
                .append("&page="+page);
                //https://api.themoviedb.org/3/search/movie?api_key=17e8cdf2666c1b20ef3323dc00c6e0e1&query=cast&page=1
        return url.toString();
    }

    public static String getPosterURL(int tmdb){
        StringBuilder url = new StringBuilder();
        url.append(POSTER_URL)
                .append(tmdb)
                .append("/images?")
                .append("api_key=")
                .append(TMDB_API_KEY);
        //https://api.themoviedb.org/3/movie/155/images?api_key=17e8cdf2666c1b20ef3323dc00c6e0e1

        return url.toString();

    }

}
