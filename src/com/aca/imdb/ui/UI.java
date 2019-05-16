package com.aca.imdb.ui;

import com.aca.imdb.engine.models.GenreType;
import com.aca.imdb.ui.models.ActionType;
import com.aca.imdb.ui.models.ContentType;
import com.aca.imdb.ui.models.MovieSearchOptions;
import com.aca.imdb.ui.models.SeriesTypes;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class UI {
    public abstract void askForAction(ArrayList<ActionType> actions);

    public abstract ActionType getAction();

    public abstract void askForMovieGenre();

    public abstract GenreType getGenre();

    public abstract void askForTitle();

    public abstract String getTitle();

    public abstract void askForUserName();

    public abstract String getUserName();

    public abstract void askForPassword();

    public abstract String getPassword();

    public abstract void print(String s);

    public abstract void askForRate();

    public abstract Double getRating();

    public abstract void askForContentType();

    public abstract ContentType getContentType();

    public abstract void askForDescription();

    public abstract String getDescription();

    public abstract void askForReleaseDate();

    public abstract LocalDate getReleaseDate();

    public abstract void askForActorName();

    public abstract String getName();

    public abstract void askForDirectorName();

    public abstract void askForWriterName();

    public abstract void askForActorBiography();

    public abstract void askForDirectorBiography();

    public abstract void askForWriterBiography();

    public abstract String getBiography();

    public abstract String getContinueAnswer();

    public abstract void askForMovieSearchOptions();

    public abstract MovieSearchOptions getMovieSearchOption();

    public abstract void askForPeriodStart();

    public abstract void askForPeriodEnd();

    public abstract LocalDate getPeriodStart();

    public abstract LocalDate getPeriodEnd();

    public abstract void askForSeriesCreateOption();

    public abstract SeriesTypes getSeriesCreateOption();
}
