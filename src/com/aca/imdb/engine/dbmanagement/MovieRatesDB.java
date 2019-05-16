package com.aca.imdb.engine.dbmanagement;

import com.aca.imdb.engine.MovieRate;
import com.aca.imdb.engine.filemanagement.Repository;

import java.util.List;

public class MovieRatesDB {
    Repository<Long, MovieRate> repository;

    public MovieRatesDB(Class<MovieRate> userRate) {
        repository = new Repository<>(userRate);
    }

    public void add(Long movieId, Long userId, Double rate) {
        MovieRate movieRate = new MovieRate(userId, movieId, rate);
        repository.write(movieId, movieRate);
    }

    public List<MovieRate> getAll(Long movieId) {
        return repository.readAll(movieId);
    }

    public MovieRate get(Long movieId) {
        return repository.read(movieId);
    }

}
