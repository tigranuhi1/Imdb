package com.aca.imdb.engine.movie.series;

import com.aca.imdb.engine.movie.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Season {
    private static Integer currentNumber = 0;
    private Integer number;
    List<Movie> episodes;

    public Season (List<Movie> movies){
        this.episodes = movies;
        number = ++currentNumber;
    }

    public List<Movie> getEpisodes(){
        return episodes;
    }
    @Override
    public String toString(){
        String episodes = Arrays.toString(this.episodes.toArray());
        return String.format("Season %s\n%s", number.toString(), episodes);
    }
}
