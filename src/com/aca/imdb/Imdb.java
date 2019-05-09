package com.aca.imdb;

import com.aca.imdb.engine.movie.Movie;
import com.aca.imdb.engine.user.*;
import com.aca.imdb.ui.GUI;
import com.aca.imdb.ui.TerminalUI;
import com.aca.imdb.ui.UI;
import com.aca.imdb.ui.models.ActionType;
import com.aca.imdb.ui.models.UIType;

public class Imdb {
    UI ui;
    UserFactory userFactory;
    RegisteredAdminUsers adminUsers;
    RegisteredUsers users;
    User user;

    public Imdb(UIType uiType) {
        userFactory = new UserFactory();
        adminUsers = RegisteredAdminUsers.getAdminUsers();
        users = RegisteredUsers.getRegisteredUsers();

        switch (uiType) {
            case TERMINAL:
                ui = new TerminalUI();
                break;
            case GUI:
                ui = new GUI();
                break;
            default:
                throw new IllegalArgumentException("Undefined user interface");
        }
    }

    public void start() {
        while (true) {
            ui.askForAction();
            ActionType action = ui.getAction();
            doAction(action);
        }
    }

    private void doAction(ActionType action) {
        switch (action) {
            case SEARCH:
                searchMovie();
                break;
            case RATE:
                rate();
                break;
            case ADD_CONTENT:
                AddContent();
                break;
            default:
                throw new IllegalArgumentException("Unknown action type.try again.");
        }
    }

    private void AddContent() {
    }

    private void rate() {
        ui.print("You must be logged in to continue.");
        User user = login();
        Movie movie = searchMovie();
        if (movie == null) {
            ui.print("No result found;");
            return;
        }
        ui.askForRate();
        Double rate = ui.getRating();
        user.rate(movie, rate);
    }

    private void register() {
        ui.askForUserName();
        String userName = ui.getUserName();

        ui.askForPassword();
        String password = ui.getPassword();

        User user = new User(userName, password);
        users.add(user);
    }

    private void adminRegister() {
        ui.askForUserName();
        String userName = ui.getUserName();

        ui.askForPassword();
        String password = ui.getPassword();

        AdminUser adminUser = new AdminUser(userName, password);
        adminUsers.add(adminUser);
    }

    private void adminLogin() {
        ui.askForUserName();
        String userName = ui.getUserName();

        ui.askForPassword();
        String password = ui.getPassword();

        if (!adminUsers.existsUser(userName, password)) {
            throw new IllegalArgumentException("Username or password is incorrect.");
        }

        user = adminUsers.get(userName);
        user.logIn();
    }

    private User login() {
        ui.askForUserName();
        String userName = ui.getUserName();

        ui.askForPassword();
        String password = ui.getPassword();

        if (!users.existsUser(userName, password)) {
            throw new IllegalArgumentException("Username or password is incorrect.");
        }

        User user = users.get(userName);
        user.logIn();
        return user;
    }

    private Movie searchMovie() {
        ui.askForMovieTitle();
        String title = ui.getTitle();
        Movie movie = User.searchByTitle(title);
        return movie;
    }

    private void searchMovieAndPrint() {
        Movie movie = searchMovie();
        if (movie == null) {
            System.out.println("No result found;");
        } else {
            System.out.println(movie.toString());
        }
    }
}
