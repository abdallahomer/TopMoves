package com.example.prog_abdallah.topmoves;

/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesInfo {
    String title;
    int date;
    String imageResource;
    String overviews;

    public MoviesInfo(String title, int date, String imageResource) {
        this.title = title;
        this.date = date;
        this.imageResource = imageResource;
    }

    public MoviesInfo() {
    }

    public MoviesInfo(String title, String overview, String imageResource, int date) {
        this.title = title;
        this.overviews = overview;
        this.imageResource = imageResource;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }


    public String getImageResource() {
        return imageResource;
    }


    public int getDate() {
        return date;
    }


    public String getOverview() {
        return overviews;
    }



}
