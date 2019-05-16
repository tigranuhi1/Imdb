package com.aca.imdb.engine.moviepeople;

import java.io.Serializable;

public class Writer extends MoviePeople  {
    private static Long nextId= 0L;
    Writer(String name, String biography) {
        super(name, biography, ++nextId);
    }
}
