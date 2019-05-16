package com.aca.imdb.engine.movie;

import com.aca.imdb.engine.models.GenreType;
import com.aca.imdb.engine.user.AdminUser;
import com.aca.imdb.engine.user.User;

import java.security.AccessControlException;
import java.time.LocalDate;

public class MovieFactory {
    public Movie createMovie(User user, GenreType genre, String title, String description, LocalDate premierDate) {
        if(!(user instanceof AdminUser)){
            throw new AccessControlException("Only a user with admin permissions can add content.");
        }
        switch (genre) {
            case DRAMA:
                return new Drama(title, description, premierDate);
            case COMEDY:
                return new Comedy(title, description, premierDate);
            case FANTASY:
                return new Fantasy(title, description, premierDate);
            case ANIMATION:
                return new Animation(title, description, premierDate);
            default:
                throw new IllegalArgumentException("Unknown genre type.");
        }
    }
}
