package com.aca.imdb.engine.movie.series;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Series implements Serializable {
    private static Long nextId = 0L;
    private final Long id = ++nextId;
    String title;
    LocalDate releaseDate;
    Double rating;
    Integer ratersCount;
    List<Season> seasons;

    public Series(String title, LocalDate releaseDate, List<Season> seasons) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.seasons = seasons;
        this.rating = 0d;
        this.ratersCount = 0;
    }

//    Series create(String title,  ArrayList<Movie> movies) {
//        ArrayList<Season> seasons = new ArrayList<>();
//        seasons.add(new Season(movies));
//        return new Series(title, seasons);
//    }
//
//    Series createWithSeasons(String title, ArrayList<Season> seasons) {
//        return new Series(title, seasons);
//    }

    public boolean hasSeasons() {
        return seasons.size() > 1 ? true : false;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        String seasons = Arrays.toString(this.seasons.toArray());
        return String.format("Title:\t%s\n" +
                "Release date:\t%s\n" +
                "Rating:\ts\n" +
                "Seasons\n%s", title, releaseDate, getRating().toString(), seasons);
    }

    private Double getRating() {
        return ratersCount == 0 ? 0 : rating / ratersCount;
    }


}
