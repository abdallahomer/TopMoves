package com.example.prog_abdallah.topmoves;

/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesInfo {
    String title;
    String date;
    String imageResource;
    String year;

    public void setOverviews(String overviews) {
        this.overviews = overviews;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public void setYear(String year) {
        this.year = year;
    }

    String overviews;

    public String getYear() {
        return year;
    }

    public MoviesInfo(String title, String date, String imageResource) {
        this.title = title;
        this.date = date;
        this.imageResource = imageResource;
    }

    public MoviesInfo() {
    }

    public MoviesInfo(String title, String overview, String imageResource, String date) {
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


    public String getDate() {
        return date;
    }


    public String getOverview() {
        return overviews;
    }



}
