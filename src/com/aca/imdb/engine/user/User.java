package com.aca.imdb.engine.user;

import com.aca.imdb.engine.HasID;

import java.io.Serializable;

public class User implements Serializable, HasID {
    private static Long nextId = 0L;
    private final Long id = ++nextId;
    private String userName;
    private String password;
//    private boolean isLoggedIn;

    User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

//    public User() {
//        isLoggedIn = false;
//    }

//    public static Movie searchByTitle(String title) {
//        Map<String, Movie> movies = Movies.getInstance().getMovies();
//        return movies.getOrDefault(title, null);
//    }

    public String getUserName() {
        return userName;
    }
    public String getPassword(){
        return password;
    }

//    public void setRate(Movie movie, Double setRate) {
//        if (!isLoggedIn) {
//            System.out.println("You must be logged in to setRate.");
//            return;
//        }
//        movie.setRate(setRate);
//    }

//    public boolean checkPasswordIsCorrect(String password) {
//        return this.password.equals(password);
//    }

//    public void logIn() {
//        isLoggedIn = true;
//    }
//
//    public void logOut() {
//        isLoggedIn = false;
//    }

    public Long getId() {
        return id;
    }
}
