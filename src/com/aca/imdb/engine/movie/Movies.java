package com.aca.imdb.engine.movie;

import java.util.HashMap;
import java.util.Map;

public class Movies {
    private static Movies instance;
    private Map<String, Movie> movies;

    private Movies(){
        movies = new HashMap<>();
    }
    public static Movies getInstance(){
        if(instance==null){
            instance =  new Movies();
        }
        return instance;
    }

    public void addMovie(Movie movie){
        movies.put(movie.getTitle(), movie);
    }

    public Movie getMovie(String title){
        return movies.getOrDefault(title, null);
    }

    public Map<String, Movie> getMovies(){
        return movies;
    }


}

