package com.aca.imdb;

import com.aca.imdb.engine.MovieRate;
import com.aca.imdb.engine.dbmanagement.MovieRatesDB;

import java.util.List;

public class RateEngine {
    public static Double getRate(Long movieId) {
        MovieRatesDB movieRates = new MovieRatesDB(MovieRate.class);
        Double rate = 0D;

        List<MovieRate> userRates = movieRates.getAll(movieId);
        for (MovieRate userRate : userRates) {
            rate += userRate.getRate();
        }

        return rate / userRates.size();
    }

    public static boolean userHasRatedMovie(Long movieId, Long userId) {
        MovieRatesDB movieRates = new MovieRatesDB(MovieRate.class);
        List<MovieRate> userRates = movieRates.getAll(movieId);
        if (userRates == null) {
            return false;
        }
        for (MovieRate userRate : userRates) {
            if (userRate.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public static void setRate(Long movieId, Long userId, Double rate) {
        MovieRatesDB movieRates = new MovieRatesDB(MovieRate.class);

        if (userHasRatedMovie(movieId, userId)) {
            throw new IllegalArgumentException("User has already rated this movie.");
        }

        movieRates.add(movieId, userId, rate);
    }
}


