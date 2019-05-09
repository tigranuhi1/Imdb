package com.aca.imdb.engine.moviepeople;

import com.aca.imdb.engine.modules.MoviePeopleType;

public class MoviePeopleFactory {
    public MoviePeople createMoviePeople(MoviePeopleType moviePeopleType, String name, String biography){
        switch (moviePeopleType){
            case ACTOR:
                return new Actor(name, biography);
            case DIRECTOR:
                return new Director(name, biography);
            case WRITER:
                return new Writer(name, biography);
            default:
                throw new IllegalArgumentException("Unknown category.");
        }
    }
}
