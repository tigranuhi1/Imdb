package com.aca.imdb.engine.movie.series;

import com.aca.imdb.engine.movie.Movie;

import java.util.ArrayList;

public class Season {
    private static Integer currentNumber = 0;
    private Integer number;
    ArrayList<Movie> movies;

    Season (ArrayList<Movie> movies){
        this.movies = movies;
        number = ++currentNumber;
    }
}
