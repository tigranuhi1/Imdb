package com.aca.imdb.engine.dbmanagement;

import com.aca.imdb.engine.filemanagement.Repository;
import com.aca.imdb.engine.movie.Movie;
import java.time.LocalDate;
import java.util.ArrayList;

public class MoviesDB<T extends Movie> {
    Repository<Long, T> movies;
    Repository<String, Long> movieTitles;
    Repository<LocalDate, Long> movieReleaseDates;

    public MoviesDB(Class<T> movie) {
        movies = new Repository<>(movie);
        movieTitles = new Repository<>(movie.getSimpleName() + " titles.txt");
        movieReleaseDates = new Repository<>((movie.getSimpleName() + " release dates.txt"));
    }

    public ArrayList<T> getMoviesReleasedInPeriod(LocalDate fromDate, LocalDate toDate) {
        ArrayList<T> movies = new ArrayList<>();
        toDate = toDate.plusDays(1);
        LocalDate releaseDate = fromDate;
        while (!releaseDate.equals(toDate)) {
            Long id = movieReleaseDates.read(releaseDate);
            if(id!=null) {
                T movie = get(id);
                if (movie != null) {
                    movies.add(movie);
                }
            }
            releaseDate = releaseDate.plusDays(1);
        }
        return movies;
    }

    public void add(T movie) {
        movies.writeUnique(movie.getId(), movie);
        movieTitles.writeUnique(movie.getTitle(), movie.getId());
        movieReleaseDates.writeUnique(movie.getReleaseDate(), movie.getId());
    }

    public T get(String title) {
        Long id = movieTitles.read(title);
        if(id==null){
            return null;
        }
        return get(id);
    }

    public T get(Long id) {
        return movies.read(id);
    }

    public boolean exists(String name) {
        T movie = get(name);
        return !(movie == null);
    }
}


