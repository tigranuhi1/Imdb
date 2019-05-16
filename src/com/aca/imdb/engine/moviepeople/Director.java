package com.aca.imdb.engine.moviepeople;

public class Director extends MoviePeople {
    private static Long nextId = 0L;
    Director(String name, String biography) {
        super(name, biography, ++nextId);
    }
}
