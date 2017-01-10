package com.example.prog_abdallah.topmoves;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 6/26/2016.
 */
public class SearchActivity extends AppCompatActivity implements LoadingMoviesFromList, TextWatcher {
    EditText editSearch;
    ListView moviesListView;
    private SearchAdapter searchAdapter;
    public static List<MoviesInfo> mMovieList;

    int page_count = 1;
    int max_pages = 100;

    MoviesUtils moviesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movies);

        editSearch = (EditText) findViewById(R.id.search_id);
        mMovieList = new ArrayList<MoviesInfo>();

        moviesListView = (ListView) findViewById(R.id.search_movies_list_view);
        setListViewAdapter();
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
        whenWriteInText(s.toString(), 0);

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
        if (scroll == 1) {
            mMovieList.addAll(movies);
        } else {
            mMovieList = movies;
        }
        searchAdapter.notifyDataSetChanged();
    }
}
