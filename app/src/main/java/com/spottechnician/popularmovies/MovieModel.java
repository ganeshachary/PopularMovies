package com.spottechnician.popularmovies;

/**
 * Created by OnesTech on 15/03/2016.
 */
public class MovieModel  {
    String  posterpath;
    String  backdrop_path;
    String overview;
    String original_title;
    String release_date;
    String vote_average;
    String id;

    public MovieModel() {

    }

    public MovieModel(String id,
                      String posterpath,
                        String  backdrop_path,
                        String overview,
                        String original_title,
                        String release_date,
                      String vote_average
    )
    {
        this.posterpath=posterpath;
        this.backdrop_path=backdrop_path;
        this.overview=overview;
        this.original_title=original_title;
        this.release_date=release_date;
        this.vote_average=vote_average;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
