package com.example.prog_abdallah.topmoves;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MoviesActivity extends AppCompatActivity {
    public static final String LOG_TAG = MoviesActivity.class.getName();
    ListView listViewMovies;
    MoviesAdapter moviesAdapter;
    int page_count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        listViewMovies = (ListView) findViewById(R.id.top_movies_list_view);


        getDataFromUrl(URLs.getTopMoviesURL("popular", page_count));
        moviesAdapter = new MoviesAdapter(this, new ArrayList<MoviesInfo>());
        listViewMovies.setAdapter(moviesAdapter);

    }

    private void getDataFromUrl(String url) {
        new MoviesAsyncTask().execute(url);
    }

    private class MoviesAsyncTask extends AsyncTask<String, String, List<MoviesInfo>> {

        @Override
        protected List<MoviesInfo> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<MoviesInfo> result = MoviesUtils.fetchMoviesData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<MoviesInfo> data) {
            if (data != null && !data.isEmpty()) {
                moviesAdapter.addAll(data);
            }
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.search_icon:
                Intent i = new Intent(this, SearchActivity.class);
                startActivity(i);

        }
        return super.onOptionsItemSelected(item);

    }
}
