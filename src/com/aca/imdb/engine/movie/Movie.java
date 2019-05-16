package com.aca.imdb.engine.movie;

import com.aca.imdb.RateEngine;
import com.aca.imdb.engine.moviepeople.MoviePeople;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Movie implements  Serializable {
    private static Long nextId = 0L;
    private final Long id = ++nextId;
    private Map<String, LinkedList<MoviePeople>> staff;
    private String title;
    private String description;
    private LocalDate releaseDate;

    public Movie(String title, String description, LocalDate premierDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = premierDate;

        initializeStaff();
    }

    private void initializeStaff() {
        this.staff = new HashMap<>();
        staff.put("Actors", new LinkedList<>());
        staff.put("Directors", new LinkedList<>());
        staff.put("Writers", new LinkedList<>());
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getRating() {
        return RateEngine.getRate(id);
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void addActor(MoviePeople actor) {
        staff.get("Actors").add(actor);
    }

    public void addDirector(MoviePeople director) {
        staff.get("Directors").add(director);
    }

    public void addWriter(MoviePeople writer) {
        staff.get("Writers").add(writer);
    }

    public Map<String, LinkedList<MoviePeople>> getStaff() {
        return staff;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        String directors = Arrays.toString(staff.get("Directors").toArray());
        String actors = Arrays.toString(staff.get("Actors").toArray());
        String writers = Arrays.toString(staff.get("Writers").toArray());
        return String.format("Title:\t%s\n" +
                "Description:\t%s\n" +
                "Rating:\t%s\n" +
                "Directors:\t%s\n" +
                "Writer:\t%s\n" +
                "Actors:\t%s\n", title, description, getRating().toString(), directors, writers, actors);
    }
}
