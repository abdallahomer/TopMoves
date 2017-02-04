package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MoviesActivity extends AppCompatActivity implements LoadingMoviesFromList {
    public static final String TAG = MoviesActivity.class.getName();
    ListView listViewMovies;
    MoviesAdapter moviesAdapter;
    int page_count = 1;
    int max_pages = 80;
    List<MoviesInfo> popularMoves;
    ImageView helpView;
    ImageView homeView;
    TextView part_1;
    TextView part_2;
    TextView part_3;
    TextView part_4;
    TextView part_5;
    TextView part_6;
    Button retry;
    View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        listViewMovies = (ListView) findViewById(R.id.top_movies_list_view);
        part_1 = (TextView) findViewById(R.id.no_internet_p1);
        part_2 = (TextView) findViewById(R.id.no_internet_p2);
        part_3 = (TextView) findViewById(R.id.no_internet_p3);
        part_4 = (TextView) findViewById(R.id.no_internet_p4);
        part_5 = (TextView) findViewById(R.id.no_internet_p5);
        part_6 = (TextView) findViewById(R.id.no_internet_p6);
        retry = (Button) findViewById(R.id.retry_id);
        container = findViewById(R.id.container_id);
        popularMoves = new ArrayList<>();
        if (CheckInternet.isOnline(getApplicationContext())) {
            getDataFromUrl(URLs.getTopMoviesURL("popular", page_count));
            moviesAdapter = new MoviesAdapter(this, popularMoves);
            listViewMovies.setAdapter(moviesAdapter);
            listViewMovies.setOnScrollListener(onScrollListener());
        } else {
            View waiting = findViewById(R.id.waiting);
            waiting.setVisibility(View.GONE);
            part_1.setVisibility(View.VISIBLE);
            part_2.setVisibility(View.VISIBLE);
            part_3.setVisibility(View.VISIBLE);
            part_4.setVisibility(View.VISIBLE);
            part_5.setVisibility(View.VISIBLE);
            part_6.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
            container.setVisibility(View.GONE);

            part_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            part_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });

        }

        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MoviesInfo movies = popularMoves.get(position);

                Intent intent = new Intent(MoviesActivity.this, MoviesDetails.class);
                intent.putExtra("title", movies.getTitle());
                intent.putExtra("year", movies.getYear());
                intent.putExtra("overview", movies.getOverview());
                intent.putExtra("tagLine", movies.getTagLine());
                intent.putExtra("runtime", movies.getRuntime());
                intent.putExtra("trailer", movies.getTrailer());
                intent.putExtra("homePage", movies.getHomePage());
                intent.putExtra("votes", movies.getVotes());
                intent.putExtra("imdb", movies.getImdb());
                intent.putExtra("tmdb", movies.getTmdb());
                startActivity(intent);

            }
        });

        getPosterImage();
        refreshActivity();


    }

    private void refreshActivity() {
        homeView = (ImageView) findViewById(R.id.home_home_id);
        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

    }

    private void getPosterImage() {
        helpView = (ImageView) findViewById(R.id.help_pic_id);
        try {
            Picasso.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/w500/3P7mVFxpMLblLbVewybYOaNqEHT.jpg")
                    .centerCrop()
                    .resize(500, 281)
                    .into(helpView);
        } catch (Exception e) {
            e.printStackTrace();
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
        if (movies != null) {
            View waiting = findViewById(R.id.waiting);
            waiting.setVisibility(View.GONE);
            popularMoves.addAll(movies);
            moviesAdapter.notifyDataSetChanged();
        } else {
            View waiting = findViewById(R.id.waiting);
            waiting.setVisibility(View.GONE);
            part_6.setText(R.string.no_movies);
        }


    }
}
