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
    private static final String TAG = MoviesUtils.class.getSimpleName();
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
        Log.i(TAG, requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
            System.out.println("Http connected");
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(String jsonResponse) {
        List<MoviesInfo> movies = new ArrayList<>();

        if (requestUrl.contains("search/")) {
            movies = extractFeatureFromJsonToSearchActivity(jsonResponse);
        }
        if (requestUrl.contains("movies")) {
            movies = extractFeatureFromJsonToMainActivity(jsonResponse);
        }
        if (requestUrl.contains("/images?")) {
            movies = extractFeatureFromJsonPoster(jsonResponse);
        }

        loadingMoviesFromList.onPopularMoviesLoaded(movies, this.scroll);
    }

    private List<MoviesInfo> extractFeatureFromJsonPoster(String moviesJSON) {
        if (TextUtils.isEmpty(moviesJSON)) {
            return null;
        }
        List<MoviesInfo> moviesList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(moviesJSON);
            JSONArray backdrops = jsonObject.getJSONArray("backdrops");

            JSONObject jsonObject1 = backdrops.getJSONObject(0);
            MoviesInfo moviesInfo = new MoviesInfo();
            if (!jsonObject1.isNull("file_path")) {
                moviesInfo.setBackdrop(jsonObject1.getString("file_path"));
            }
            JSONArray posters = jsonObject.getJSONArray("posters");
            JSONObject jsonObject2 = posters.getJSONObject(0);
            if (!jsonObject2.isNull("file_path")){
                moviesInfo.setPoster(jsonObject2.getString("file_path"));
            }
            Log.i(TAG, "extractFeatureFromJsonPoster: " + jsonObject1.getString("file_path") +"   "+ jsonObject2.getString("file_path"));
            moviesList.add(moviesInfo);


            return moviesList;

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movieQuake JSON results", e);
        }

        return null;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
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
                //Log.i(TAG, jsonResponse);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the movie JSON results.", e);
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
                if (!jsonObject.isNull("year"))
                    moviesInfo.setYear(jsonObject.getString("year"));
                if (!jsonObject.isNull("tagline"))
                    moviesInfo.setTagLine(jsonObject.getString("tagline"));
                if (!jsonObject.isNull("runtime"))
                    moviesInfo.setRuntime(jsonObject.getInt("runtime"));
                if (!jsonObject.isNull("trailer"))
                    moviesInfo.setTrailer(jsonObject.getString("trailer"));
                if (!jsonObject.isNull("homepage"))
                    moviesInfo.setHomePage(jsonObject.getString("homepage"));
                if (!jsonObject.isNull("rating"))
                    moviesInfo.setRating(jsonObject.getDouble("rating"));
                if (!jsonObject.isNull("votes"))
                    moviesInfo.setVotes(jsonObject.getInt("votes"));
                if (!jsonObject.isNull("ids")) {
                    JSONObject ids = jsonObject.getJSONObject("ids");
                    if (!ids.isNull("tmdb"))
                        moviesInfo.setTmdb(ids.getInt("tmdb"));
                }
                if (!jsonObject.isNull("genres")) {
                    JSONArray genres = jsonObject.getJSONArray("genres");
                    moviesInfo.setGenres(genres.toString());
                }

                Log.i(TAG, moviesInfo.getOverview() + " +++ " + moviesInfo.getGenres() + " +++ " + moviesInfo.getTmdb() + " +++ " + moviesInfo.getYear() + " +++ " + moviesInfo.getTitle() + " +++ " + moviesInfo.getHomePage() + " +++ " + moviesInfo.getVotes() + " +++ " + moviesInfo.getTagLine() + " +++ " + moviesInfo.getTrailer() + " +++ " + moviesInfo.getRating() + " +++ " + moviesInfo.getDate() + " +++ " + moviesInfo.getRuntime());

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
        System.out.println("abdallah" + moviesJSON);

        try {

            JSONObject jsonObject = new JSONObject(moviesJSON);
            if (!jsonObject.isNull("results")) {
                JSONArray results = jsonObject.getJSONArray("results");


                for (int i = 0; i < results.length(); i++) {
                    JSONObject det = results.getJSONObject(i);
                    MoviesInfo moviesInfo = new MoviesInfo();
                    if (!det.isNull("poster_path"))
                        moviesInfo.setSearchPoster(det.getString("poster_path"));
                    if (!det.isNull("overview"))
                        moviesInfo.setSearchOverview(det.getString("overview"));
                    if (!det.isNull("release_date"))
                        moviesInfo.setSearchReleaseYear(det.getString("release_date"));
                    if (!det.isNull("genre_ids")) {
                        JSONArray genres = det.getJSONArray("genre_ids");
                        moviesInfo.setSearchGenrs(genres.toString());
                    }
                    if (!det.isNull("id"))
                        moviesInfo.setSearchTMDB(det.getInt("id"));
                    if (!det.isNull("title"))
                        moviesInfo.setSearchTitle(det.getString("title"));
                    if (!det.isNull("backdrop_path"))
                        moviesInfo.setSearchBackdrop(det.getString("backdrop_path"));
                    if (!det.isNull("vote_count"))
                        moviesInfo.setSearchVotes(det.getInt("vote_count"));
                    if (!det.isNull("vote_average"))
                        moviesInfo.setSearchRate(det.getDouble("vote_average"));

                    moviesList.add(moviesInfo);

                    Log.i(TAG, moviesInfo.getSearchRate() + "");
                    Log.i(TAG, moviesInfo.getSearchOverview());
                    Log.i(TAG, moviesInfo.getSearchTMDB() + "");
                    Log.i(TAG, moviesInfo.getSearchReleaseYear());
                    Log.i(TAG, moviesInfo.getSearchGenrs());
                    Log.i(TAG, moviesInfo.getSearchVotes() + "");
                    Log.i(TAG, moviesInfo.getSearchTitle());
                }
            }

            return moviesList;

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movieQuake JSON results", e);
        }

        return null;
    }


}
