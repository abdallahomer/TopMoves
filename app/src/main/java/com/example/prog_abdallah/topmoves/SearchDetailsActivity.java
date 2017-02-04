package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, LoadingMoviesFromList, LoadingTrailerFromJSON {
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
    TextView reviewView;
    int tmdb;
    String trailer1;
    YouTubePlayerView youTubePlayerView;
    View trailerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.search_youtube_id);
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
        reviewView = (TextView) findViewById(R.id.search_details_reviews_id);
        youTubePlayerView.initialize(URLs.YOUTUBE_ANDROID_PLAYER_API, this);
        trailerView = findViewById(R.id.search_trailer1_id);

        if (CheckInternet.isOnline(getApplicationContext())) {
            getDateFromIntent();
            getTrailerFromURL(URLs.getTrailerURL(tmdb));
            getReviewFromURL(URLs.getReviewsURL(tmdb));
        } else {
            Intent i = new Intent(SearchDetailsActivity.this,NoInternetInSearchActivity.class);
            startActivity(i);
        }


    }

    private void getTrailerFromURL(String url) {
        new MoviesUtils(getApplicationContext(), url, this, this).execute();
    }

    private void getReviewFromURL(String url) {
        new MoviesUtils(context, url, this).execute();
    }

    private void getDateFromIntent() {
        intent = this.getIntent();
        tmdb = intent.getExtras().getInt("id");


        titleView.setText(intent.getStringExtra("title") + " | Trailer [HD]");
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
                Intent i = new Intent(SearchDetailsActivity.this, HomePageActivity.class);
                i.putExtra("homePage", "https://www.themoviedb.org/movie/" + tmdb);
                startActivity(i);
            }
        });

    }


    @Override
    public void onPopularMoviesLoaded(List<MoviesInfo> movies, int scroll) {
        if (movies.isEmpty()) {
            reviewView.setText("No Reviews now...");
        } else {
            String reviews = movies.get(0).getReviews();
            System.out.println(reviews);
            reviewView.setText(reviews);
        }
    }

    @Override
    public void onTrailerLoaded(List<String> trailer) {
        if (!trailer.isEmpty()) {
            trailer1 = trailer.get(0);
            trailerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(URLs.YOUTUBE + trailer1));
                    startActivity(i);
                }
            });

        }else
        trailerView.setVisibility(View.GONE);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(URLs.YOUTUBE + trailer1.split("=")[1]);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {


        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
