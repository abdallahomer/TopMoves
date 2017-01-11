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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MoviesActivity extends AppCompatActivity implements LoadingMoviesFromList {
    public static final String LOG_TAG = MoviesActivity.class.getName();
    ListView listViewMovies;
    MoviesAdapter moviesAdapter;
    int page_count = 1;
    int max_pages = 80;
    List<MoviesInfo> popularMoves;
    TextView noInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        listViewMovies = (ListView) findViewById(R.id.top_movies_list_view);
        popularMoves = new ArrayList<>();
        noInternet = (TextView)findViewById(R.id.no_internet);

        if(CheckInternet.isOnline(getApplicationContext())){
            View waiting = findViewById(R.id.waiting);
            waiting.setVisibility(View.GONE);
            getDataFromUrl(URLs.getTopMoviesURL("popular", page_count));
            moviesAdapter = new MoviesAdapter(this, popularMoves);
            listViewMovies.setAdapter(moviesAdapter);
            listViewMovies.setOnScrollListener(onScrollListener());
        }else{
            View waiting = findViewById(R.id.waiting);
            waiting.setVisibility(View.GONE);
            noInternet.setText(R.string.no_internet);
        }


    }

    private void getDataFromUrl(String url) {
        new MoviesUtils(this, url, this).execute();
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

    @Override
    public void onPopularMoviesLoaded(List<MoviesInfo> movies, int scroll) {

        popularMoves.addAll(movies);
        moviesAdapter.notifyDataSetChanged();
    }
}
