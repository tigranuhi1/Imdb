package com.aca.imdb.engine.moviepeople;

import java.util.LinkedList;

public abstract class MoviePeople {
    private String name;
    private LinkedList movies;
    private String biography;

    MoviePeople(String name, String biography){
        this.name = name;
        this.biography = biography;
    }

    public String toString(){
        return String.format("Name:\t%s\n"+
                "Biography:\t%s\n", name, biography);
    }
}
