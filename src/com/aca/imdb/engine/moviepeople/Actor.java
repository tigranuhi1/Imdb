package com.aca.imdb.engine.moviepeople;

public class Actor extends MoviePeople {
    private static Long nextId = 0L;

    public Actor(String name, String biography) {
        super(name, biography, ++nextId);
    }
}
