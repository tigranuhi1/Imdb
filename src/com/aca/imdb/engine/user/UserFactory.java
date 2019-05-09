package com.aca.imdb.engine.user;

import com.aca.imdb.engine.modules.UserType;

public class UserFactory {
    public User createUser(UserType userType, String userName, String password) {
        switch (userType) {
            case USER:
                return new User(userName, password);
//                RegisteredUsers registeredUsers = RegisteredUsers.getRegisteredUsers();
//                registeredUsers.add(user);
            case ADMIN_USER:
                return new AdminUser(userName, password);
//                RegisteredAdminUsers registeredAdminUsers = RegisteredAdminUsers.getAdminUsers();
//                registeredAdminUsers.add(adminUser);
            default:
                throw new IllegalArgumentException("Unknown user type");
        }
    }

    public User createDefaultUser() {
        return new User();
    }
}

