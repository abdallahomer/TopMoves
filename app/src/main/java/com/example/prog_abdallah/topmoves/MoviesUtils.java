package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public final class MoviesUtils extends AsyncTask<String, String, String> {
    private static final String LOG_TAG = MoviesUtils.class.getSimpleName();
    private LoadingMoviesFromList loadingMoviesFromList;
    Context context;
    String requestUrl;
    int scroll;

    public MoviesUtils(Context context, String requestUrl, LoadingMoviesFromList loadingMoviesFromList) {
        this.context = context;
        this.requestUrl = requestUrl;
        this.loadingMoviesFromList = loadingMoviesFromList;
        scroll = 0;
    }

    public MoviesUtils(Context context, String requestUrl, int scroll, LoadingMoviesFromList loadingMoviesFromList) {
        this.context = context;
        this.requestUrl = requestUrl;
        this.loadingMoviesFromList = loadingMoviesFromList;
        this.scroll = scroll;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = createUrl(requestUrl);
        Log.i(LOG_TAG, requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
            System.out.println("Http connected");
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(String jsonResponse) {
        List<MoviesInfo> movies;

        if (requestUrl.contains("?query=")) {
            movies = extractFeatureFromJsonToSearchActivity(jsonResponse);
        } else {
            movies = extractFeatureFromJsonToMainActivity(jsonResponse);
        }

        loadingMoviesFromList.onPopularMoviesLoaded(movies, this.scroll);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("trakt-api-version", "2");
            urlConnection.setRequestProperty("trakt-api-key", URLs.CLIENT_ID);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                Log.i(LOG_TAG, jsonResponse);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the movie JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<MoviesInfo> extractFeatureFromJsonToMainActivity(String moviesJSON) {
        if (TextUtils.isEmpty(moviesJSON)) {
            return null;
        }
        List<MoviesInfo> moviesList = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(moviesJSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MoviesInfo moviesInfo = new MoviesInfo();

                if (!jsonObject.isNull("title"))
                    moviesInfo.setTitle(jsonObject.getString("title"));
                if (!jsonObject.isNull("released"))
                    moviesInfo.setDate(jsonObject.getString("released"));
                if (!jsonObject.isNull("overview"))
                    moviesInfo.setOverviews(jsonObject.getString("overview"));
                if (!jsonObject.isNull("ids")) {
                    String imdb = jsonObject.getJSONObject("ids").getString("imdb");
                    moviesInfo.setImageResource(imdb);

                }
                moviesList.add(moviesInfo);
            }
            return moviesList;
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movieQuake JSON results", e);
        }

        return null;
    }

    private static List<MoviesInfo> extractFeatureFromJsonToSearchActivity(String moviesJSON) {
        if (TextUtils.isEmpty(moviesJSON)) {
            return null;
        }
        List<MoviesInfo> moviesList = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(moviesJSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MoviesInfo movies = new MoviesInfo();

                if (!jsonObject.isNull("movie")) {
                    JSONObject movie = jsonObject.getJSONObject("movie");

                    if (!movie.isNull("title"))
                        movies.setTitle(movie.getString("title"));
                    if (!movie.isNull("year"))
                        movies.setYear(movie.getInt("year"));
                    if (!movie.isNull("overview"))
                        movies.setOverviews(movie.getString("overview"));
                    if (!jsonObject.isNull("ids")) {
                        String imdb = jsonObject.getJSONObject("ids").getString("imdb");
                        movies.setImageResource(imdb);
                    }


                    Log.i(LOG_TAG, movies.getTitle());
                    Log.i(LOG_TAG, movies.getYear() + " ");
                    Log.i(LOG_TAG, movies.getImageResource() + "  ");
                    Log.i(LOG_TAG, movies.getOverview() + "");


                    moviesList.add(movies);
                }
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movieQuake JSON results", e);
        }

        return moviesList;
    }


}
