package com.example.prog_abdallah.topmoves;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by ProG_AbdALlAh on 1/9/2017.
 */

public class SearchAdapter extends BaseAdapter {
    Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return SearchActivity.mMovieList.size();
    }

    @Override
    public Object getItem(int i) {
        return SearchActivity.mMovieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.search_result_item, parent, false);

        MoviesInfo moviesUtilities = (MoviesInfo) getItem(position);
        System.out.println("abdallah omer    " + moviesUtilities.getSearchRate() + ""+
        moviesUtilities.getSearchOverview()+
        moviesUtilities.getSearchTMDB() + ""+
        moviesUtilities.getSearchReleaseYear()+
        moviesUtilities.getSearchVotes() + ""+
        moviesUtilities.getSearchTitle());

        TextView titleView = (TextView) convertView.findViewById(R.id.search_movieTitle_id);
        TextView releaseDateView = (TextView) convertView.findViewById(R.id.search_movieDate_id);
        ImageView posterView = (ImageView) convertView.findViewById(R.id.search_image);
        ImageView backdropView = (ImageView)convertView.findViewById(R.id.search_backdrop_id);

        String title = moviesUtilities.getSearchTitle();
        titleView.setText(title);

        String year = moviesUtilities.getSearchReleaseYear();
        releaseDateView.setText(year);

        try {
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+moviesUtilities.getSearchPoster()).resize(500, 724)
                    .centerCrop()
                    .into(posterView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500"+moviesUtilities.getSearchBackdrop()).resize(500, 281)
                    .centerCrop()
                    .into(backdropView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
