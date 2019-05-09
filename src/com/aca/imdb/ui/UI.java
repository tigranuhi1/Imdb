package com.aca.imdb.ui;

import com.aca.imdb.ui.models.ActionType;

public abstract class UI {
    public abstract void askForAction();

    public abstract ActionType getAction();

    public abstract void askForMovieTitle();

    public abstract String getTitle();

    public abstract void askForUserName();
    public abstract String getUserName();
    public abstract void askForPassword();
    public abstract String getPassword();

    public abstract void print(String s);

    public abstract void askForRate();
    public abstract Double getRating();
}
