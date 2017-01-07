package com.example.prog_abdallah.topmoves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesAdapter extends ArrayAdapter<MoviesInfo> {

    public MoviesAdapter(Context context, List<MoviesInfo> movies) {
        super(context, 0, movies);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        MoviesInfo moviesUtilities = getItem(position);

        ImageView moviesView = (ImageView) listItemView.findViewById(R.id.image);
        int movImage = moviesUtilities.getImageResource();
        moviesView.setImageResource(movImage);

        TextView titleView = (TextView)listItemView.findViewById(R.id.movieTitle);
        String title = moviesUtilities.getTitle();
        titleView.setText(title);

        TextView releaseDateView = (TextView)listItemView.findViewById(R.id.movieDate);
        String releaseDate = moviesUtilities.getDate();
        releaseDateView.setText(releaseDate);

        return listItemView;
    }
}

