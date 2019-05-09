package com.aca.imdb.ui;

import com.aca.imdb.ui.models.ActionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TerminalUI extends UI {
    private Scanner sc;
    private Map<Integer, ActionType> actionTypes;

    public TerminalUI() {
        sc = new Scanner(System.in);
        initialize();
    }

//    private void initialize() {
//        actionTypes = new HashMap<>();
//        actionTypes.put(1, ActionType.SEARCH);
//        actionTypes.put(2, ActionType.LOGIN);
//        actionTypes.put(3, ActionType.ADMIN_LOGIN);
//        actionTypes.put(4, ActionType.REGISTER);
//        actionTypes.put(5, ActionType.ADMIN_REGISTER);
//    }

    private void initialize() {
        actionTypes = new HashMap<>();
        actionTypes.put(1, ActionType.SEARCH);
        actionTypes.put(2, ActionType.RATE);
        actionTypes.put(3, ActionType.ADD_CONTENT);
    }

    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void askForRate() {
        print("Type rating.");
    }

    @Override
    public Double getRating(){
        return sc.nextDouble();
    }

    @Override
    public void askForMovieTitle() {
        print("Type movie title.");
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

//    @Override
//    public void askForAction() {
//        print("What would you like to do?\n" +
//                "Search for a movie:\t1\n" +
//                "Log in:\t2\n" +
//                "Log in as admin:\t3\n" +
//                "Register:\t4\n" +
//                "Register as admin:\t5");
//    }

    @Override
    public void askForAction() {
        print("What would you like to do?\n" +
                "Search for a movie:\t1\n" +
                "Rate movie:\t2\n" +
                "Add content:\t3\n");
    }

//    @Override
//    public ActionType getAction() {
//        Integer actionType = sc.nextInt();
//        while (!actionTypes.containsKey(actionType)) {
//            print("Incorrect action. Try again..");
//            actionType = sc.nextInt();
//        }
//        sc.nextLine();  //to empty scanner for next input
//        return actionTypes.get(actionType);
//    }

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
}
