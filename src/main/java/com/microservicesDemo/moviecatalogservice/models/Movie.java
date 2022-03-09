package com.microservicesDemo.moviecatalogservice.models;

public class Movie {
 private String movieId;
 private String Name;

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        Name = name;
    }

    public Movie() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
