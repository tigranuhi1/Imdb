package com.aca.imdb.engine.movie;

import com.aca.imdb.engine.Rateable;
import com.aca.imdb.engine.moviepeople.Actor;
import com.aca.imdb.engine.moviepeople.Director;
import com.aca.imdb.engine.moviepeople.MoviePeople;
import com.aca.imdb.engine.moviepeople.Writer;
import org.omg.PortableInterceptor.INACTIVE;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Movie implements Rateable {
    Map<String, LinkedList<MoviePeople>> staff;
    private String title;
    private String description;
    private Double rating;
    private Integer ratersCount;
    private LocalDate premierDate;

    Movie(String title, String description, LocalDate premierDate) {
        this.title = title;
        this.description = description;
        this.premierDate = premierDate;
        this.rating = 0d;
        this.ratersCount = 0;

        initializeStaff();
    }

    private void initializeStaff() {
        this.staff = new HashMap<>();
        staff.put("Actors", new LinkedList<>());
        staff.put("Directors", new LinkedList<>());
        staff.put("Writers", new LinkedList<>());
    }

    @Override
    public void rate(Double rating) {
        this.rating += rating;
        ratersCount++;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getRating() {
        return rating/ratersCount;
    }

    public LocalDate getPremierDate() {
        return premierDate;
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

    public String toString() {
        String directors = Arrays.toString(staff.get("Directors").toArray());
        String actors = Arrays.toString(staff.get("Actors").toArray());
        String writers = Arrays.toString(staff.get("Writers").toArray());
        return String.format("Title:\t%s\n" +
                "Description:\t%s\n" +
                "Directors:\t%s\n" +
                "Writer:\t%s\n" +
                "Actors:\n\t%s\n", title, description, directors, writers, actors);
    }
}
