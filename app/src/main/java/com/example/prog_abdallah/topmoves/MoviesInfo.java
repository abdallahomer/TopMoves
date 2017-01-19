package com.example.prog_abdallah.topmoves;

/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesInfo {
    String title;
    String date;
    String imageResource;
    String year;
    String overviews;
    String tagLine;
    int runtime;
    String trailer;
    String homePage;

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    String genres;

    public int getTmdb() {
        return tmdb;
    }

    public void setTmdb(int tmdb) {
        this.tmdb = tmdb;
    }

    int tmdb;

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    double rating;
    int votes;


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


    public String getYear() {
        return year;
    }


    public MoviesInfo() {
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
