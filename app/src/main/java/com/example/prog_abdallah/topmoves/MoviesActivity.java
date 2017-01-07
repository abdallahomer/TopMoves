package com.example.prog_abdallah.topmoves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {
    ListView movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        movies = (ListView)findViewById(R.id.list_view);

        List<MoviesUtilities> l = new ArrayList<>();

        l.add(new MoviesUtilities("Divergent", "15 april 2015",R.drawable.divergent));
        l.add(new MoviesUtilities("Doctor Strange", "23 dec 2016",R.drawable.doctor_strange));
        l.add(new MoviesUtilities("Fast and Furious", "2 mar 2010",R.drawable.fast_and_furious));
        l.add(new MoviesUtilities("Inception", "3 may 2007",R.drawable.inception));
        l.add(new MoviesUtilities("Beautiful Mind", "10 jul 2000",R.drawable.beautiful_mind));
        l.add(new MoviesUtilities("Sky Fall", "15 april 2015",R.drawable.sky_fall));
        l.add(new MoviesUtilities("The Hobbit", "20 aug 2005",R.drawable.the_hobbit));
        l.add(new MoviesUtilities("Twilight", "30 jan 2011",R.drawable.twilight));
        l.add(new MoviesUtilities("V For Vendetta", "15 oct 2015",R.drawable.vfor_vendetta));
        l.add(new MoviesUtilities("The Expendable", "25 april 2014",R.drawable.the_expendable));

        MoviesAdapter moviesAdapter = new MoviesAdapter(this,l);
        movies.setAdapter(moviesAdapter);


    }
}
