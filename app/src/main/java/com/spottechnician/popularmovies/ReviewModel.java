package com.spottechnician.popularmovies;

/**
 * Created by OnesTech on 22/04/2016.
 */
public class ReviewModel {
    String author;
    String content;

    public ReviewModel(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
