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

import java.util.List;

/**
 * Created by ProG_AbdALlAh on 1/9/2017.
 */

public class SearchAdapter extends ArrayAdapter<MoviesInfo> {
    Context context;

    public SearchAdapter(Context context) {
        super(context,0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.search_result_item, parent, false);
        }

        MoviesInfo moviesUtilities = getItem(position);

        ImageView moviesView = (ImageView) listItemView.findViewById(R.id.search_image);
        try {
            Picasso.with(context)
                    .load(moviesUtilities.getImageResource()).resize(150, 150)
                    .centerCrop()
                    .into(moviesView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Details",moviesUtilities.getTitle()+moviesUtilities.getYear()+moviesUtilities.getOverview());
        TextView overview = (TextView)listItemView.findViewById(R.id.search_overview_id);
        String ov = moviesUtilities.getOverview();
        overview.setText(ov);
        System.out.println("abdallah"+overview);

        TextView titleView = (TextView) listItemView.findViewById(R.id.search_movieTitle_id);
        String title = moviesUtilities.getTitle();
        titleView.setText(title);

        TextView releaseDateView = (TextView) listItemView.findViewById(R.id.search_movieDate_id);
        int year = moviesUtilities.getYear();
        releaseDateView.setText(year);



        return listItemView;
    }
}
