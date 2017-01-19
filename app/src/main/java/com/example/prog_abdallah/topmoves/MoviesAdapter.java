package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesAdapter extends ArrayAdapter<MoviesInfo> {

    Context context;
    TextView titleView;
    TextView releaseDateView;
    TextView ratingView;
    ImageView posterView;
    TextView genreComedy;
    TextView genreRomance;
    TextView genreCrime;
    TextView genreNone;
    TextView genreScienceFiction;
    TextView genreDrama;
    TextView genreAnimation;
    TextView genreAdventure;
    TextView genreAction;



    public MoviesAdapter(Context context, List<MoviesInfo> movies) {
        super(context, 0, movies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, viewGroup, false);
        }

        MoviesInfo moviesUtilities = getItem(position);
        System.out.println(moviesUtilities.getGenres() +
                " +++ " + moviesUtilities.getTmdb() +
                " +++ " + moviesUtilities.getTitle() +
                " +++ " + moviesUtilities.getRating() +
                " +++ " + moviesUtilities.getDate());

        titleView = (TextView) listItemView.findViewById(R.id.movieTitle);
        String title = moviesUtilities.getTitle();
        titleView.setText(title);

        releaseDateView = (TextView) listItemView.findViewById(R.id.movieDate);
        String releaseDate = moviesUtilities.getDate();
        releaseDateView.setText(releaseDate);

        ratingView = (TextView) listItemView.findViewById(R.id.rate_num_id);
        double rate = moviesUtilities.getRating();
        ratingView.setText(formattedRate(rate));

        posterView = (ImageView)listItemView.findViewById(R.id.image_view_id);
        int tmdb = moviesUtilities.getTmdb();
        try{
            Picasso.with(context)
                    .load("https://api.themoviedb.org/3/movie/"+tmdb+"/images?api_key=17e8cdf2666c1b20ef3323dc00c6e0e1")
                    .centerCrop()
                    .resize(150,150)
                    .into(posterView);
        }catch (Exception e){
            e.printStackTrace();
        }

        String genres = moviesUtilities.getGenres();
        genreAction = (TextView)listItemView.findViewById(R.id.action);
        genreAdventure = (TextView)listItemView.findViewById(R.id.adventure);
        genreAnimation = (TextView)listItemView.findViewById(R.id.animation);
        genreComedy = (TextView)listItemView.findViewById(R.id.comedy);
        genreCrime = (TextView)listItemView.findViewById(R.id.crime);
        genreDrama = (TextView)listItemView.findViewById(R.id.drama);
        genreNone = (TextView)listItemView.findViewById(R.id.none);
        genreRomance = (TextView)listItemView.findViewById(R.id.romance);
        genreScienceFiction = (TextView)listItemView.findViewById(R.id.science_fiction);
        if (genres!=null && !genres.equals("")){
            if (genres.contains("action"))
                genreAction.setVisibility(View.VISIBLE);
            else
                genreAction.setVisibility(View.GONE);
            if (genres.contains("adventure"))
                genreAdventure.setVisibility(View.VISIBLE);
            else
                genreAdventure.setVisibility(View.GONE);
            if (genres.contains("animation"))
                genreAnimation.setVisibility(View.VISIBLE);
            else
                genreAnimation.setVisibility(View.GONE);
            if (genres.contains("comedy"))
                genreComedy.setVisibility(View.VISIBLE);
            else
                genreComedy.setVisibility(View.GONE);
            if (genres.contains("crime"))
                genreCrime.setVisibility(View.VISIBLE);
            else
                genreCrime.setVisibility(View.GONE);
            if (genres.contains("drama"))
                genreDrama.setVisibility(View.VISIBLE);
            else
                genreDrama.setVisibility(View.GONE);
            if (genres.contains("romance"))
                genreRomance.setVisibility(View.VISIBLE);
            else
                genreRomance.setVisibility(View.GONE);
            if (genres.contains("science-fiction"))
                genreScienceFiction.setVisibility(View.VISIBLE);
            else
                genreScienceFiction.setVisibility(View.GONE);
        }else{
            genreNone.setVisibility(View.VISIBLE);
        }


        return listItemView;
    }

    private String formattedRate(double rate) {
        DecimalFormat formattedRate = new DecimalFormat("0.0");
        return formattedRate.format(rate);
    }


}