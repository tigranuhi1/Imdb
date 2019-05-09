package com.aca.imdb.engine.movie.series;

import com.aca.imdb.engine.Rateable;
import com.aca.imdb.engine.movie.Movie;
import java.util.ArrayList;

public class Series implements Rateable {
    String title;
    Double rating;
    Integer ratersCount;
    ArrayList<Season> seasons;

    private Series(String title, ArrayList<Season> seasons) {
        this.title = title;
        this.seasons = seasons;
        this.rating = 0d;
        this.ratersCount = 0;
    }

    Series create(String title,  ArrayList<Movie> movies) {
        ArrayList<Season> seasons = new ArrayList<>();
        seasons.add(new Season(movies));
        return new Series(title, seasons);
    }

    Series createWithSeasons(String title, ArrayList<Season> seasons) {
        return new Series(title, seasons);
    }

    public boolean hasSeasons() {
        return seasons.size() > 1 ? true : false;
    }


    @Override
    public void rate(Double rating) {
            this.rating = (this.rating + rating) / ++ratersCount;
    }
}
