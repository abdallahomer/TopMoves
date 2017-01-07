package com.example.prog_abdallah.topmoves;

/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesInfo {
    String title;
    String date;
    int imageResource;

    public MoviesInfo(String title, String date, int imageResource) {
        this.title = title;
        this.date = date;
        this.imageResource = imageResource;
    }

    public MoviesInfo() {
    }

    public MoviesInfo(String title, String overview, int imageResource, String date) {
        this.title = title;
        Overview = overview;
        this.imageResource = imageResource;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    String Overview;

}
