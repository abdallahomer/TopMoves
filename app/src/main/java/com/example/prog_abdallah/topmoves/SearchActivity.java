package com.example.prog_abdallah.topmoves;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProG_AbdALlAh on 1/7/2017.
 */

public class SearchActivity extends AppCompatActivity {
    ListView searchListView;
    int page_count = 1;
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movies);

        searchListView = (ListView) findViewById(R.id.search_movies_list_view);

        //getDataFromUrl(URLs.getTopMoviesURL("popular", page_count));
        searchAdapter = new SearchAdapter(this, new ArrayList<MoviesInfo>());
        searchListView.setAdapter(searchAdapter);
        //searchListView.setOnScrollListener(onScrollListener());


    }

    private void getDataFromUrl(String url) {
        new SearchAsyncTask().execute(url);
    }


    public class SearchAsyncTask extends AsyncTask<String, String, List<MoviesInfo>> {

        @Override
        protected List<MoviesInfo> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

           // List<MoviesInfo> result = MoviesUtils.fetchMoviesData(urls[0]);
            return null;

        }

        @Override
        protected void onPostExecute(List<MoviesInfo> data) {
            if (data != null && !data.isEmpty()) {
                searchAdapter.addAll(data);
            }
        }
    }

}
