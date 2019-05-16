package com.aca.imdb.ui;

import com.aca.imdb.engine.models.GenreType;
import com.aca.imdb.ui.models.ActionType;
import com.aca.imdb.ui.models.ContentType;
import com.aca.imdb.ui.models.MovieSearchOptions;
import com.aca.imdb.ui.models.SeriesTypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TerminalUI extends UI {
    private Scanner sc;
    private Map<Integer, ActionType> actionTypes;
    private Map<Integer, GenreType> genreTypes;
    private Map<Integer, ContentType> contentTypes;
    private Map<Integer, MovieSearchOptions> movieSearchOptions;
    private Map<Integer, SeriesTypes> seriesTypes;

    public TerminalUI() {
        sc = new Scanner(System.in);
        initialize();
    }

    private void initialize() {
        Integer key = 1;

        actionTypes = new HashMap<>();
        actionTypes.put(key++, ActionType.SEARCH);
        actionTypes.put(key++, ActionType.SIGN_UP);
        actionTypes.put(key++, ActionType.ADMIN_SIGN_UP);
        actionTypes.put(key++, ActionType.LOG_IN);
        actionTypes.put(key++, ActionType.ADMIN_LOG_IN);
        actionTypes.put(key++, ActionType.LOG_OUT);
        actionTypes.put(key++, ActionType.RATE);
        actionTypes.put(key++, ActionType.ADD_CONTENT);

        key = 1;
        genreTypes = new HashMap<>();
        genreTypes.put(key++, GenreType.DRAMA);
        genreTypes.put(key++, GenreType.COMEDY);
        genreTypes.put(key++, GenreType.FANTASY);
        genreTypes.put(key++, GenreType.ANIMATION);
        genreTypes.put(key++, GenreType.ANY);

        key = 1;
        contentTypes = new HashMap<>();
        contentTypes.put(key++, ContentType.ACTOR);
        contentTypes.put(key++, ContentType.DIRECTOR);
        contentTypes.put(key++, ContentType.WRITER);
        contentTypes.put(key++, ContentType.MOVIE);
        contentTypes.put(key++, ContentType.SERIES);

        key = 1;
        movieSearchOptions = new HashMap<>();
        movieSearchOptions.put(key++, MovieSearchOptions.BY_TITLE);
        movieSearchOptions.put(key++, MovieSearchOptions.IN_PERIOD);

        key = 1;
        seriesTypes = new HashMap<>();
        seriesTypes.put(key++, SeriesTypes.WITH_SEASONS);
        seriesTypes.put(key++, SeriesTypes.WITHOUT_SEASONS);
    }

    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void askForRate() {
        print("Type rating.");
    }

    @Override
    public Double getRating() {
        return sc.nextDouble();
    }

    @Override
    public ContentType getContentType() {
        Integer contentType = sc.nextInt();
        while (!contentTypes.containsKey(contentType)) {
            print("Incorrect option. Try again..");
            contentType = sc.nextInt();
        }
        sc.nextLine();  //to empty scanner for next input
        return contentTypes.get(contentType);
    }

    @Override
    public void askForContentType() {
        Integer len = 9;
        print("Type content type to add.\n" +
                padRight("ACTOR", len) + "1\n" +
                padRight("DIRECTOR", len) + "2\n" +
                padRight("WRITER", len) + "3\n" +
                padRight("MOVIE", len) + "4\n" +
                padRight("SERIES", len) + "5\n");
    }

    @Override
    public void askForDescription() {
        print("Type movie description.");
    }

    @Override
    public String getDescription() {
        return sc.nextLine();
    }

    @Override
    public void askForReleaseDate() {
        print("Type release date (dd-MM-yyyy)");
    }

    @Override
    public void askForActorName() {
        print("Type actor's name.");
    }

    @Override
    public String getName() {
        return sc.nextLine();
    }

    @Override
    public String getBiography() {
        return sc.nextLine();
    }

    @Override
    public void askForActorBiography() {
        print("Type actor's short bio.");
    }

    @Override
    public void askForDirectorName() {
        print("Type director's name.");
    }

    @Override
    public void askForDirectorBiography() {
        print("Type director's short bio.");
    }

    @Override
    public void askForWriterBiography() {
        print("Type writer's short bio.");
    }

    @Override
    public void askForWriterName() {
        print("Type writer's name.");
    }

    @Override
    public void askForPeriodStart() {
        print("Type start date");
    }

    @Override
    public void askForPeriodEnd() {
        print("Type end date.");
    }

    @Override
    public LocalDate getPeriodStart() {
        return getDate();
    }

    @Override
    public void askForSeriesCreateOption() {
        Integer len = 18;
        print(padRight("With seasons", len) + "1\n" +
                padRight("Without seasons", len) + "2");
    }

    @Override
    public SeriesTypes getSeriesCreateOption() {
        Integer seriesCreateOption = sc.nextInt();
        while (!seriesTypes.containsKey(seriesCreateOption)) {
            print("Incorrect option. Try again..");
            seriesCreateOption = sc.nextInt();
        }
        sc.nextLine();  //to empty scanner for next input
        return seriesTypes.get(seriesCreateOption);
    }

    @Override
    public LocalDate getPeriodEnd() {
        return getDate();
    }

    private LocalDate getDate(){
        String date = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    @Override
    public MovieSearchOptions getMovieSearchOption() {
        Integer movieSearchOption = sc.nextInt();
        while (!movieSearchOptions.containsKey(movieSearchOption)) {
            print("Incorrect option. Try again..");
            movieSearchOption = sc.nextInt();
        }
        sc.nextLine();  //to empty scanner for next input
        return movieSearchOptions.get(movieSearchOption);
    }

    @Override
    public void askForMovieSearchOptions() {
        Integer len = 12;
        print(padRight("BY TITLE:", len) + "1\n" +
                padRight("IN PERIOD:", len) + "2");
    }

    @Override
    public String getContinueAnswer() {
        return sc.nextLine();
    }

    @Override
    public LocalDate getReleaseDate() {
        return getDate();
    }

    @Override
    public void askForTitle() {
        print("Type title.");
    }

    @Override
    public String getTitle() {
        return sc.nextLine();
    }

    @Override
    public void askForUserName() {
        print("Type your user name.");
    }

    @Override
    public String getUserName() {
        return sc.nextLine();
    }

    @Override
    public void askForPassword() {
        print("Type your password.");
    }

    @Override
    public String getPassword() {
        return sc.nextLine();
    }

    @Override
    public void askForAction(ArrayList<ActionType> actions) {
        Integer len = 15;
        String actionPrintText = "What would you like to do?\n";
        for (ActionType action : actions) {
            Set<Integer> keys = actionTypes.keySet();
            for (Integer key : keys) {
                if (actionTypes.get(key).equals(action)) {
                    actionPrintText += String.format("%s%s\n", padRight(action.toString(), len), key);
                    break;
                }
            }
        }

        print(actionPrintText);
    }

    @Override
    public ActionType getAction() {
        Integer actionType = sc.nextInt();
        while (!actionTypes.containsKey(actionType)) {
            print("Incorrect action. Try again..");
            actionType = sc.nextInt();
        }
        sc.nextLine();  //to empty scanner for next input
        return actionTypes.get(actionType);
    }

    @Override
    public void askForMovieGenre() {
        Integer len = 11;
        print("Type genre\n" +
                padRight("DRAMA", len) + "1\n" +
                padRight("COMEDY", len) + "2\n" +
                padRight("FANTASY", len) + "3\n" +
                padRight("ANIMATION", len) + "4\n" +
                padRight("ALL", len) + "5");
    }

    @Override
    public GenreType getGenre() {
        Integer genreType = sc.nextInt();
        while (!genreTypes.containsKey(genreType)) {
            print("Incorrect genre. Try again..");
            genreType = sc.nextInt();
        }
        sc.nextLine();  //to empty scanner for next input
        return genreTypes.get(genreType);
    }

    private String padRight(String string, int len) {
        return String.format("%-" + len + "s", string);
    }

    private String padLeft(String string, int len) {
        return String.format("%" + len + "s", string);
    }
}
