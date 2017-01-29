package com.example.prog_abdallah.topmoves;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProG_AbdALlAh on 1/16/2017.
 */
public class SearchActivity extends AppCompatActivity implements LoadingMoviesFromList, TextWatcher {
    EditText editSearch;
    ListView moviesListView;
    private SearchAdapter searchAdapter;
    public static List<MoviesInfo> mMovieList;
    TextView noInternet;

    int page_count = 1;
    int max_pages = 100;

    MoviesUtils moviesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movies);

        editSearch = (EditText) findViewById(R.id.search_id);
        noInternet = (TextView) findViewById(R.id.no_internet_id);
        mMovieList = new ArrayList<>();

        moviesListView = (ListView) findViewById(R.id.search_movies_list_view);
        if (!CheckInternet.isOnline(getApplicationContext())) {
            noInternet.setText(R.string.no_internet);
        } else {
            noInternet.setText("");
        }
        setListViewAdapter();
        moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                MoviesInfo movies = mMovieList.get(position);
                Intent i = new Intent(SearchActivity.this,SearchDetailsActivity.class);
                i.putExtra("overview",movies.getSearchOverview());
                i.putExtra("release_date",movies.getSearchReleaseYear());
                i.putExtra("genre_ids",movies.getSearchGenrs());
                i.putExtra("id",movies.getSearchTMDB());
                i.putExtra("title",movies.getSearchTitle());
                i.putExtra("backdrop_path",movies.getSearchBackdrop());
                i.putExtra("vote_count",movies.getSearchVotes());
                i.putExtra("vote_average",movies.getSearchRate());
                i.putExtra("poster_path",movies.getSearchPoster());

                startActivity(i);
            }
        });
        moviesListView.setOnScrollListener(onScrollListener());

        editSearch.addTextChangedListener(this);

    }

    private void setListViewAdapter() {
        searchAdapter = new SearchAdapter(this);
        moviesListView.setAdapter(searchAdapter);
    }

    private MoviesUtils getDataFromUrl(String url, int scroll) {
        return (MoviesUtils) new MoviesUtils(this, url, scroll, this).execute();
    }

    private AbsListView.OnScrollListener onScrollListener() {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = moviesListView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (moviesListView.getLastVisiblePosition() >= count - threshold && page_count < max_pages) {
                        page_count++;
                        whenWriteInText(editSearch.getText().toString(), 1);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (CheckInternet.isOnline(getApplicationContext())){
            whenWriteInText(s.toString(), 0);
        }
        noInternet.setText("");

    }


    public void whenWriteInText(String s, int scroll) {
        String query = s.toString().replace(" ", "%20");

        if (s.length() >= 3) {
            String url = URLs.getSearchURL(query, page_count);

            if (moviesUtils != null && moviesUtils.getStatus() == AsyncTask.Status.RUNNING) {
                moviesUtils.cancel(true);
            }
            moviesUtils = getDataFromUrl(url, scroll);

        }
    }

    @Override
    public void onPopularMoviesLoaded(List<MoviesInfo> movies, int scroll) {
        if (movies!=null){
            if (scroll == 1) {
                mMovieList.addAll(movies);
            } else {
                mMovieList = movies;
            }
            searchAdapter.notifyDataSetChanged();
        }else
        {
            noInternet.setText(R.string.no_match_movies);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,MoviesActivity.class);
        startActivity(i);
    }
}
