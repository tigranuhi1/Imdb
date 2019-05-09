package com.aca.imdb.engine.user;

import com.aca.imdb.engine.movie.Movie;
import com.aca.imdb.engine.movie.Movies;

import java.util.Map;

public class AdminUser extends User {
    public AdminUser(String userName, String password) {
        super(userName, password);
    }
    public AdminUser(){
        super();
    }

    public void addMovie(Movie movie){
        Map<String, Movie> movies = Movies.getInstance().getMovies();
        if(movies.containsKey(movie.getTitle())){
            throw new IllegalArgumentException("A movie with this title already exists.");
        }
        movies.put(movie.getTitle(), movie);
    }
}
