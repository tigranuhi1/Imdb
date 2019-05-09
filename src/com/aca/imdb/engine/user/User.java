package com.aca.imdb.engine.user;

import com.aca.imdb.engine.movie.Movie;
import com.aca.imdb.engine.movie.Movies;

import java.util.Map;

public class User {
    private String userName;
    private String password;
    private boolean isLoggedIn;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
        isLoggedIn = false;
    }

    public String getUserName() {
        return userName;
    }

    public void rate(Movie movie, Double rate) {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to rate.");
            return;
        }
        movie.rate(rate);
    }

    public static Movie searchByTitle(String title) {
        Map<String, Movie> movies = Movies.getInstance().getMovies();
        return movies.getOrDefault(title, null);
    }

    public boolean checkPasswordIsCorrect(String password) {
        return this.password.equals(password);
    }

    public void logIn() {
        isLoggedIn = true;
    }

    public void logOut() {
        isLoggedIn = false;
    }
}
