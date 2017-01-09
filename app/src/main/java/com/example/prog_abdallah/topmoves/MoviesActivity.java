package com.example.prog_abdallah.topmoves;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MoviesActivity extends AppCompatActivity {
    public static final String LOG_TAG = MoviesActivity.class.getName();
    ListView listViewMovies;
    MoviesAdapter moviesAdapter;
    int page_count = 1;
    int max_pages = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        listViewMovies = (ListView) findViewById(R.id.top_movies_list_view);


        getDataFromUrl(URLs.getTopMoviesURL("popular", page_count));
        moviesAdapter = new MoviesAdapter(this, new ArrayList<MoviesInfo>());
        listViewMovies.setAdapter(moviesAdapter);
        listViewMovies.setOnScrollListener(onScrollListener());

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

    private AbsListView.OnScrollListener onScrollListener() {

        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = listViewMovies.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listViewMovies.getLastVisiblePosition() >= count - threshold && page_count < max_pages) {
                        page_count++;
                        getDataFromUrl(URLs.getTopMoviesURL("popular", page_count));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
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
