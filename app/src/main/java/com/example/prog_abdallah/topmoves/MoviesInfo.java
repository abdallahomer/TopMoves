package com.example.prog_abdallah.topmoves;

/**
 * Created by ProG_AbdALlAh on 1/6/2017.
 */

public class MoviesInfo {
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    String poster;
    String title;
    String date;
    String year;
    String overviews;
    String tagLine;
    int runtime;
    String trailer;
    String homePage;
    String searchTitle;
    String searchReleaseYear;
    String searchOverview;
    String searchPoster;
    String searchGenrs;

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    String backdrop;

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchReleaseYear() {
        return searchReleaseYear;
    }

    public void setSearchReleaseYear(String searchReleaseYear) {
        this.searchReleaseYear = searchReleaseYear;
    }

    public String getSearchOverview() {
        return searchOverview;
    }

    public void setSearchOverview(String searchOverview) {
        this.searchOverview = searchOverview;
    }

    public String getSearchPoster() {
        return searchPoster;
    }

    public void setSearchPoster(String searchPoster) {
        this.searchPoster = searchPoster;
    }

    public String getSearchGenrs() {
        return searchGenrs;
    }

    public void setSearchGenrs(String searchGenrs) {
        this.searchGenrs = searchGenrs;
    }

    public int getSearchTMDB() {
        return searchTMDB;
    }

    public void setSearchTMDB(int searchTMDB) {
        this.searchTMDB = searchTMDB;
    }

    public String getSearchBackdrop() {
        return searchBackdrop;
    }

    public void setSearchBackdrop(String searchBackdrop) {
        this.searchBackdrop = searchBackdrop;
    }

    public int getSearchVotes() {
        return searchVotes;
    }

    public void setSearchVotes(int searchVotes) {
        this.searchVotes = searchVotes;
    }

    public double getSearchRate() {
        return searchRate;
    }

    public void setSearchRate(double searchRate) {
        this.searchRate = searchRate;
    }

    int searchTMDB;
    String searchBackdrop;
    int searchVotes;
    double searchRate;

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


    public String getDate() {
        return date;
    }


    public String getOverview() {
        return overviews;
    }


}
