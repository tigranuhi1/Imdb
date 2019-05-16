package com.aca.imdb.engine;

import java.io.Serializable;

public class MovieRate implements Serializable {
    private final Long userId;
    private final Long movieId;
    private final Double rate;

    public MovieRate(Long userId, Long movieId, Double rate) {
        this.userId = userId;
        this.movieId = movieId;
        this.rate = rate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Double getRate(){
        return rate;
    }


}
