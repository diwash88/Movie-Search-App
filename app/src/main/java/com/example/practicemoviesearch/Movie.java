package com.example.practicemoviesearch;

public class Movie {

    private String title;
    private double rating;
    private String description;
    private String posterUrl;
    public Movie() {
    }

    public Movie(String title, double rating, String description, String posterUrl) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for rating
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
