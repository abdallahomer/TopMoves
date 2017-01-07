package com.example.prog_abdallah.topmoves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {
    ListView movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        movies = (ListView)findViewById(R.id.top_movies_list_view);

        List<MoviesInfo> l = new ArrayList<>();

        MoviesAdapter moviesAdapter = new MoviesAdapter(this,l);
        movies.setAdapter(moviesAdapter);


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
