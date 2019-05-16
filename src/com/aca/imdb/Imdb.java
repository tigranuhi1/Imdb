package com.aca.imdb;

import com.aca.imdb.engine.dbmanagement.DB;
import com.aca.imdb.engine.models.GenreType;
import com.aca.imdb.engine.models.MoviePeopleType;
import com.aca.imdb.engine.models.UserType;
import com.aca.imdb.engine.movie.*;
import com.aca.imdb.engine.movie.series.Season;
import com.aca.imdb.engine.movie.series.Series;
import com.aca.imdb.engine.moviepeople.*;
import com.aca.imdb.engine.user.*;
import com.aca.imdb.ui.GUI;
import com.aca.imdb.ui.TerminalUI;
import com.aca.imdb.ui.UI;
import com.aca.imdb.ui.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Imdb {
    UI ui;
    UserFactory userFactory;
    MovieFactory movieFactory;
    MoviePeopleFactory moviePeopleFactory;
    DB db;
    User currentUser;

    public Imdb(UIType uiType) {
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
        db = DB.getInstance();
        userFactory = new UserFactory();
        movieFactory = new MovieFactory();
        moviePeopleFactory = new MoviePeopleFactory();
    }

    public void start() {
        while (true) {
            ArrayList<ActionType> actions = getPossibleActions();
            ui.askForAction(actions);
            ActionType action = ui.getAction();
            execute(action);
        }
    }

    private ArrayList<ActionType> getPossibleActions() {
        ArrayList<ActionType> actions = new ArrayList<>();
        actions.add(ActionType.SEARCH);
        if (currentUser == null) {
            actions.add(ActionType.SIGN_UP);
            actions.add(ActionType.ADMIN_SIGN_UP);
            actions.add(ActionType.LOG_IN);
            actions.add(ActionType.ADMIN_LOG_IN);
        } else {
            actions.add(ActionType.LOG_OUT);
            actions.add(ActionType.RATE);
            if (currentUser instanceof AdminUser) {
                actions.add(ActionType.ADD_CONTENT);
            }
        }
        return actions;
    }

    private void execute(ActionType action) {
        switch (action) {
            case SEARCH:
                searchMovieAndPrint();
                break;
            case RATE:
                rate();
                break;
            case ADD_CONTENT:
                addContent();
                break;
            case LOG_IN:
                login();
                break;
            case ADMIN_LOG_IN:
                adminLogin();
                break;
            case SIGN_UP:
                signUp();
                break;
            case ADMIN_SIGN_UP:
                adminSignUp();
                break;
            case LOG_OUT:
                logOut();
                break;
            default:
                throw new IllegalArgumentException("Unknown action type.try again.");
        }
    }

    private void addContent() {
        ui.askForContentType();
        ContentType contentType = ui.getContentType();

        switch (contentType) {
            case ACTOR:
                addActor();
                break;
            case DIRECTOR:
                addDirector();
                break;
            case WRITER:
                addWriter();
                break;
            case MOVIE:
                addMovie();
                break;
            case SERIES:
                addSeries();
                break;
            default:
                throw new IllegalArgumentException("Trying to add content of unknown type.");
        }

    }

    private void addSeries() {
        ui.askForSeriesCreateOption();
        SeriesTypes option = ui.getSeriesCreateOption();

        switch (option) {
            case WITH_SEASONS:
                addSeriesWithSeasons();
                break;
            case WITHOUT_SEASONS:
                addSeriesWithoutSeasons();
                break;
            default:
                throw new IllegalArgumentException("Unknown type for series.");
        }
    }

    private void addSeriesWithSeasons() {
        ui.askForTitle();
        String title = ui.getTitle();
        ui.askForReleaseDate();
        LocalDate releaseDate = ui.getReleaseDate();
        List<Season> seasons = new ArrayList<>();

        String exitKey;
        ui.print("Adding seasons...");
        do {
            seasons.add(createSeason());
            ui.print("Continue adding? y - yes, n - no.");
            exitKey = ui.getContinueAnswer();
        } while (!exitKey.equals("n"));

        db.getSeries().add(new Series(title, releaseDate, seasons));
    }

    private void addSeriesWithoutSeasons() {
        ui.askForTitle();
        String title = ui.getTitle();
        ui.askForReleaseDate();
        LocalDate releaseDate = ui.getReleaseDate();

        List<Season> seasons = new ArrayList<>();
        seasons.add(createSeason());
        db.getSeries().add(new Series(title, releaseDate, seasons));
    }

    private Season createSeason() {
        List<Movie> movies = new ArrayList<>();
        String exitKey;
        ui.print("Adding episodes...");
        do {
            movies.add(createMovie());
            ui.print("Continue adding? y - yes, n - no.");
            exitKey = ui.getContinueAnswer();
        } while (!exitKey.equals("n"));
        return new Season(movies);
    }

    private void addWriter() {
        db.getWriters().add(createWriter());
    }

    private Writer createWriter() {
        ui.askForWriterName();
        String name = ui.getName();
        ui.askForWriterBiography();
        String bio = ui.getBiography();
        return (Writer) moviePeopleFactory.createMoviePeople(MoviePeopleType.WRITER, name, bio);
    }

    private void addActor() {
        db.getActors().add(createActor());
    }

    private Actor createActor() {
        ui.askForActorName();
        String name = ui.getName();
        ui.askForActorBiography();
        String bio = ui.getBiography();
        return (Actor) moviePeopleFactory.createMoviePeople(MoviePeopleType.ACTOR, name, bio);
    }

    private void logOut() {
        currentUser = null;
    }

    private Movie createMovie() {
        ui.askForMovieGenre();
        GenreType genreType = ui.getGenre();
        ui.askForTitle();
        String title = ui.getTitle();
        ui.askForDescription();
        String description = ui.getDescription();
        ui.askForReleaseDate();
        LocalDate releaseDate = ui.getReleaseDate();
        Movie movie = movieFactory.createMovie(currentUser, genreType, title, description, releaseDate);
        addActorsToMovie(movie);
        addDirectorsToMovie(movie);
        addWritersToMovie(movie);

        return movie;
    }

    private void addMovie() {
        //currentUser = userFactory.createUser(UserType.ADMIN_USER, "aa", "a");
        Movie movie = createMovie();

        switch (movie.getClass().getSimpleName()) {
            case "Drama":
                db.getDramas().add((Drama) movie);
                break;
            case "Comedy":
                db.getComedies().add((Comedy) movie);
                break;
            case "Fantasy":
                db.getFantasies().add((Fantasy) movie);
                break;
            case "Animation":
                db.getAnimations().add((Animation) movie);
                break;
            default:
                throw new IllegalArgumentException("Unknown genre.");
        }
    }

    private void addActorsToMovie(Movie movie) {
        String exitKey;
        do {
            movie.addActor(createActor());
            ui.print("Continue adding? y - yes, n - no.");
            exitKey = ui.getContinueAnswer();
        } while (!exitKey.equals("n"));
    }

    private void addDirectorsToMovie(Movie movie) {
        String exitKey;
        do {
            movie.addDirector(createDirector());
            ui.print("Continue adding? y - yes, n - no.");
            exitKey = ui.getContinueAnswer();
        } while (!exitKey.equals("n"));
    }

    private Director createDirector() {
        ui.askForDirectorName();
        String name = ui.getName();
        ui.askForDirectorBiography();
        String bio = ui.getBiography();
        return (Director) moviePeopleFactory.createMoviePeople(MoviePeopleType.DIRECTOR, name, bio);
    }

    private void addDirector() {
        db.getDirectors().add(createDirector());
    }

    private void addWritersToMovie(Movie movie) {
        String exitKey;
        do {
            movie.addWriter(createWriter());
            ui.print("Continue adding? y - yes, n - no.");
            exitKey = ui.getContinueAnswer();
        } while (!exitKey.equals("n"));
    }

    private void rate() {
        if (currentUser == null) {
            ui.print("You must be logged in to continue.");
            login();
        }
        Movie movie = searchMovieByTitle();
        if (movie == null) {
            ui.print("No result found;");
            return;
        }

        if(RateEngine.userHasRatedMovie(movie.getId(), currentUser.getId())){
            ui.print("You have already rated this movie.");
            return;
        }

        ui.askForRate();
        Double rate = ui.getRating();


        db.getMovieRates().add(movie.getId(), currentUser.getId(), rate);
    }

    private void signUp() {
        ui.askForUserName();
        String userName = ui.getUserName();

        if (db.getUsers().exists(userName)) {
            ui.print("A user with this username already exists");
            return;
        }

        ui.askForPassword();
        String password = ui.getPassword();

        User user = userFactory.createUser(UserType.USER, userName, password);

        db.getUsers().add(user);
        currentUser = user;
    }

    private void adminSignUp() {
        ui.askForUserName();
        String userName = ui.getUserName();

        if (db.getAdminUsers().exists(userName)) {
            ui.print("A user with this username already exists");
            return;
        }

        ui.askForPassword();
        String password = ui.getPassword();

        AdminUser adminUser = (AdminUser) userFactory.createUser(UserType.ADMIN_USER, userName, password);
        db.getAdminUsers().add(adminUser);
        currentUser = adminUser;
    }

    private void adminLogin() {
        ui.askForUserName();
        String userName = ui.getUserName();

        ui.askForPassword();
        String password = ui.getPassword();

        AdminUser adminUser = db.getAdminUsers().get(userName);
        if (adminUser != null && checkPasswordIsCorrect(adminUser, password)) {
            currentUser = adminUser;
        } else {
            ui.print("Incorrect username or password.");
        }
    }

    private void login() {
        ui.askForUserName();
        String userName = ui.getUserName();

        ui.askForPassword();
        String password = ui.getPassword();

        User user = db.getUsers().get(userName);
        if (user != null && checkPasswordIsCorrect(user, password)) {
            currentUser = user;
        } else {
            ui.print("Incorrect username or password.");
        }
    }

    private boolean checkPasswordIsCorrect(User user, String password) {
        return user.getPassword().equals(password);
    }

    private Movie searchMovieByTitle() {
        ui.askForMovieGenre();
        GenreType genre = ui.getGenre();
        ui.askForTitle();
        String title = ui.getTitle();

        Movie movie;
        switch (genre) {
            case ANY:
                movie = db.getDramas().get(title);
                if (movie == null) {
                    movie = db.getComedies().get(title);
                }
                if (movie == null) {
                    movie = db.getFantasies().get(title);
                }
                if (movie == null) {
                    movie = db.getAnimations().get(title);
                }
                break;
            case DRAMA:
                movie = db.getDramas().get(title);
                break;
            case COMEDY:
                movie = db.getComedies().get(title);
                break;
            case FANTASY:
                movie = db.getFantasies().get(title);
                break;
            case ANIMATION:
                movie = db.getAnimations().get(title);
                break;
            default:
                throw new IllegalArgumentException("Unknown genre");
        }

        return movie;
    }

    private void searchMovieAndPrint() {
        ui.askForMovieSearchOptions();
        MovieSearchOptions movieSearchOptions = ui.getMovieSearchOption();
        List<Movie> movies = new ArrayList<>();
        switch (movieSearchOptions) {
            case BY_TITLE:
                Movie movie = searchMovieByTitle();
                if (movie != null) {
                    movies.add(movie);
                }
                break;
            case IN_PERIOD:
                movies = searchMoviesInPeriod();
                break;
            default:
                throw new IllegalArgumentException("Unknown search option.");
        }


        if (movies.size() == 0) {
            System.out.println("No result found;");
        } else {
            for (Movie movie : movies) {
                ui.print(movie.toString());
            }
        }
    }

    private List<Movie> searchMoviesInPeriod() {
        List<Movie> moviesInPeriod = new ArrayList<>();
        ui.askForPeriodStart();
        LocalDate fromDate = ui.getPeriodStart();

        ui.askForPeriodEnd();
        LocalDate toDate = ui.getPeriodEnd();
        moviesInPeriod.addAll(db.getDramas().getMoviesReleasedInPeriod(fromDate, toDate));
        moviesInPeriod.addAll(db.getComedies().getMoviesReleasedInPeriod(fromDate, toDate));
        moviesInPeriod.addAll(db.getFantasies().getMoviesReleasedInPeriod(fromDate, toDate));
        moviesInPeriod.addAll(db.getAnimations().getMoviesReleasedInPeriod(fromDate, toDate));

        return moviesInPeriod;
    }
}
