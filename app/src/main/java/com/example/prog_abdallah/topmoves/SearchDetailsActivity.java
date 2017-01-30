package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SearchDetailsActivity extends AppCompatActivity {
    ImageView posterView;
    TextView titleView;
    ImageView homeView;
    TextView releaseView;
    TextView rateView;
    TextView voteView;
    TextView overview;
    TextView genreComedy;
    TextView genreRomance;
    TextView genreCrime;
    TextView genreNone;
    TextView genreScienceFiction;
    TextView genreDrama;
    TextView genreAnimation;
    TextView genreAdventure;
    TextView genreAction;
    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);

        posterView = (ImageView) findViewById(R.id.search_details_backdrop_id);
        titleView = (TextView) findViewById(R.id.search_details_title_id);
        homeView = (ImageView) findViewById(R.id.search_details_home_id);
        releaseView = (TextView) findViewById(R.id.search_details_year_id);
        rateView = (TextView) findViewById(R.id.search_details_rate_id);
        voteView = (TextView) findViewById(R.id.search_details_vote_id);
        overview = (TextView) findViewById(R.id.search_details_overview_id);
        genreAction = (TextView) findViewById(R.id.search_action);
        genreAdventure = (TextView) findViewById(R.id.search_adventure);
        genreAnimation = (TextView) findViewById(R.id.search_animation);
        genreComedy = (TextView) findViewById(R.id.search_comedy);
        genreCrime = (TextView) findViewById(R.id.search_crime);
        genreDrama = (TextView) findViewById(R.id.search_drama);
        genreNone = (TextView) findViewById(R.id.search_none);
        genreRomance = (TextView) findViewById(R.id.search_romance);
        genreScienceFiction = (TextView) findViewById(R.id.search_science_fiction);

        getDateFromIntent();

    }

    private void getDateFromIntent() {
        intent = this.getIntent();

        try {
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + intent.getStringExtra("poster_path")).resize(500, 500)
                    .centerCrop()
                    .into(posterView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        titleView.setText(intent.getStringExtra("title")+ " | Poster");
        releaseView.setText(intent.getStringExtra("release_date"));
        rateView.setText(intent.getDoubleExtra("vote_average", 5) + "");
        voteView.setText(intent.getIntExtra("vote_count", 8255) + "");
        overview.setText(intent.getStringExtra("overview"));

        String genres = intent.getStringExtra("genre_ids");
        if (genres != null && !genres.equals("")) {
            if (genres.contains("28"))
                genreAction.setVisibility(View.VISIBLE);
            else
                genreAction.setVisibility(View.GONE);
            if (genres.contains("10749"))
                genreRomance.setVisibility(View.VISIBLE);
            else
                genreRomance.setVisibility(View.GONE);
            if (genres.contains("12"))
                genreAdventure.setVisibility(View.VISIBLE);
            else
                genreAdventure.setVisibility(View.GONE);
            if (genres.contains("16"))
                genreAnimation.setVisibility(View.VISIBLE);
            else
                genreAnimation.setVisibility(View.GONE);
            if (genres.contains("35"))
                genreComedy.setVisibility(View.VISIBLE);
            else
                genreComedy.setVisibility(View.GONE);
            if (genres.contains("80"))
                genreCrime.setVisibility(View.VISIBLE);
            else
                genreCrime.setVisibility(View.GONE);
            if (genres.contains("18"))
                genreDrama.setVisibility(View.VISIBLE);
            else
                genreDrama.setVisibility(View.GONE);
            if (genres.contains("878"))
                genreScienceFiction.setVisibility(View.VISIBLE);
            else
                genreScienceFiction.setVisibility(View.GONE);

        } else
            genreNone.setVisibility(View.VISIBLE);

        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchDetailsActivity.this,HomePageActivity.class);
                i.putExtra("homePage","https://www.themoviedb.org/movie/"+intent.getExtras().getInt("id"));
                startActivity(i);
            }
        });

    }


}
