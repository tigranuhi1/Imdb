package com.aca.imdb.engine.moviepeople;

import com.aca.imdb.engine.HasID;
import java.io.Serializable;

public abstract class MoviePeople implements Serializable, HasID {
    private final Long id;
    private String name;
    private String biography;

    MoviePeople(String name, String biography, Long id) {
        this.name = name;
        this.biography = biography;
        this.id = id;
    }

    public String toString() {
        return String.format("Name:\t%s, " + "Biography:\t%s", name, biography);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
