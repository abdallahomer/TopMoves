package com.example.prog_abdallah.topmoves;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movies);

        searchListView = (ListView) findViewById(R.id.search_movies_list_view);

        List<MoviesInfo> n = new ArrayList<>();
        n.add(new MoviesInfo("Inception", "This is a fantastic film", "ImageUrl", "12-5-2008"));

        searchListView.setAdapter(new SearchAdapter(this, n));
    }
}
