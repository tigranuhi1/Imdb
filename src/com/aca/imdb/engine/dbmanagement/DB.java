package com.aca.imdb.engine.dbmanagement;

import com.aca.imdb.engine.MovieRate;
import com.aca.imdb.engine.movie.Animation;
import com.aca.imdb.engine.movie.Comedy;
import com.aca.imdb.engine.movie.Drama;
import com.aca.imdb.engine.movie.Fantasy;
import com.aca.imdb.engine.movie.series.Series;
import com.aca.imdb.engine.moviepeople.Actor;
import com.aca.imdb.engine.moviepeople.Director;
import com.aca.imdb.engine.moviepeople.Writer;
import com.aca.imdb.engine.user.AdminUser;
import com.aca.imdb.engine.user.User;

public class DB {
    private static DB instance;
    private MoviePeopleDB<Actor> actors;
    private MoviePeopleDB<Director> directors;
    private MoviePeopleDB<Writer> writers;
    private UsersDB<User> users;
    private UsersDB<AdminUser> adminUsers;
    private MoviesDB<Drama> dramas;
    private MoviesDB<Comedy> comedies;
    private MoviesDB<Fantasy> fantasies;
    private MoviesDB<Animation> animations;
    private SeriesDB series;
    private MovieRatesDB movieRates;

    private DB(){
        actors = new MoviePeopleDB<>(Actor.class);
        directors = new MoviePeopleDB<>(Director.class);
        writers = new MoviePeopleDB<>(Writer.class);
        users = new UsersDB<>(User.class);
        adminUsers = new UsersDB<>(AdminUser.class);
        animations = new MoviesDB<>(Animation.class);
        dramas = new MoviesDB<>(Drama.class);
        fantasies = new MoviesDB<>(Fantasy.class);
        comedies = new MoviesDB<>(Comedy.class);
        series = new SeriesDB(Series.class);
        movieRates = new MovieRatesDB(MovieRate.class);
    }

    public static DB getInstance(){
        if(instance==null){
            instance = new DB();
        }
        return instance;
    }

    public MovieRatesDB getMovieRates(){
        return movieRates;
    }
    public MoviePeopleDB<Actor> getActors(){
        return actors;
    }

    public MoviePeopleDB<Director> getDirectors(){
        return directors;
    }

    public MoviePeopleDB<Writer> getWriters(){
        return writers;
    }

    public MoviesDB<Drama> getDramas(){
        return dramas;
    }

    public MoviesDB<Comedy> getComedies(){
        return comedies;
    }

    public MoviesDB<Fantasy> getFantasies(){
        return fantasies;
    }

    public MoviesDB<Animation> getAnimations(){
        return animations;
    }

    public UsersDB<User> getUsers(){
        return users;
    }

    public UsersDB<AdminUser> getAdminUsers(){
        return adminUsers;
    }
    public SeriesDB getSeries(){
        return series;
    }
}
