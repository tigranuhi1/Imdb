package com.aca.imdb.engine.dbmanagement;

import com.aca.imdb.engine.filemanagement.Repository;
import com.aca.imdb.engine.movie.series.Series;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeriesDB {
    Repository<Long, Series> series;
    Repository<String, Long> seriesTitles;
    Repository<LocalDate, Long> seriesReleaseDates;

    public SeriesDB(Class<Series> series) {
        this.series = new Repository<>(series);
        seriesTitles = new Repository<>(series.getSimpleName() + " titles.txt");
    }

    public List<Series> getMoviesReleasedInPeriod(LocalDate fromDate, LocalDate toDate) {
        List<Series> series = new ArrayList<>();
        toDate = toDate.plusDays(1);
        LocalDate releaseDate = fromDate;
        while (!releaseDate.equals(toDate)) {
            Long id = seriesReleaseDates.read(releaseDate);
            if (id != null) {
                Series aSeries = get(id);
                if (series != null) {
                    series.add(aSeries);
                }
            }
            releaseDate = releaseDate.plusDays(1);
        }
        return series;
    }

    public void add(Series aSeries) {
        series.writeUnique(aSeries.getId(), aSeries);
        seriesTitles.writeUnique(aSeries.getTitle(), aSeries.getId());
        seriesReleaseDates.writeUnique(aSeries.getReleaseDate(), aSeries.getId());
    }

    public Series get(String title) {
        Long id = seriesTitles.read(title);
        if (id == null) {
            return null;
        }
        return get(id);
    }

    public Series get(Long id) {
        return series.read(id);
    }

    public boolean exists(String title) {
        Series movie = get(title);
        return !(movie == null);
    }
}


