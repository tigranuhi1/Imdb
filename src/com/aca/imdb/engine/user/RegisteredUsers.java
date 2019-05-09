package com.aca.imdb.engine.user;

import java.util.HashMap;
import java.util.Map;

public class RegisteredUsers {
    private static RegisteredUsers instance;
    private Map<String, User> users;

    private RegisteredUsers() {
        users = new HashMap<>();
    }

    public static RegisteredUsers getRegisteredUsers() {
        if (instance == null) {
            instance = new RegisteredUsers();
        }
        return instance;
    }

    public void add(User user) {
        if (users.containsKey(user.getUserName())) {
            throw new IllegalArgumentException("A user with this user name already exists. " +
                    "Try another name.");
        }
        users.put(user.getUserName(), user);
            }

    public User get(String userName) {
        return users.getOrDefault(userName, null);
    }

    public boolean existsUser(String userName, String password) {
        User user = users.getOrDefault(userName, null);
        if(user==null){
            return false;
        }

        return user.checkPasswordIsCorrect(password);
    }
}
