package com.example.prog_abdallah.topmoves;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MoviesDetails extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String TAG = MoviesActivity.class.getName();


    YouTubePlayerView youTubePlayerView;
    TextView titleView;
    ImageView homeView;
    TextView clockView;
    TextView votesView;
    TextView yearView;
    TextView taglineView;
    TextView overviewView;
    Intent intent;
    String home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_id);
        titleView = (TextView) findViewById(R.id.details_title_id);
        homeView = (ImageView) findViewById(R.id.home_view_id);
        clockView = (TextView) findViewById(R.id.details_runtime_id);
        votesView = (TextView) findViewById(R.id.details_vote_id);
        yearView = (TextView) findViewById(R.id.details_year_id);
        taglineView = (TextView) findViewById(R.id.details_tagline_id);
        overviewView = (TextView) findViewById(R.id.details_overview_id);

        intent = getIntent();

        init();
    }

    private void init() {
        home = intent.getStringExtra("homePage");
        Log.i(TAG, "init:             "+home);
        titleView.setText(intent.getStringExtra("title") + " | Trailer [HD]");
        clockView.setText(intent.getIntExtra("runtime", 152) + "  min");
        votesView.setText(intent.getExtras().getInt("votes") + "");
        yearView.setText(intent.getStringExtra("year"));
        taglineView.setText(intent.getStringExtra("tagLine"));
        overviewView.setText(intent.getStringExtra("overview"));
        youTubePlayerView.initialize(URLs.YOUTUBE_ANDROID_PLAYER_API, this);
        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MoviesDetails.this, HomePageActivity.class);
                intent.putExtra("homePage",home);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {
            youTubePlayer.cueVideo(intent.getStringExtra("trailer").split("=")[1]);
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MoviesDetails.this,MoviesActivity.class);
        startActivity(i);
    }
}