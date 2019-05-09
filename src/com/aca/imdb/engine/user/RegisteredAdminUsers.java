package com.aca.imdb.engine.user;

import java.util.HashMap;
import java.util.Map;

public class RegisteredAdminUsers {
    private static RegisteredAdminUsers instance;
    private Map<String, AdminUser> adminUsers;

    private RegisteredAdminUsers(){
        adminUsers = new HashMap<>();
    }

    public static RegisteredAdminUsers getAdminUsers(){
        if(instance==null){
            instance =  new RegisteredAdminUsers();
        }
        return instance;
    }

    public void add(AdminUser adminUser){
        if(adminUsers.containsKey(adminUser.getUserName())){
            throw new IllegalArgumentException("An admin user with this name already exists. " +
                    "Try another name.");
        }
        adminUsers.put(adminUser.getUserName(), adminUser);

    }

    public boolean existsUser(String userName, String password) {
        User user = adminUsers.getOrDefault(userName, null);
        if(user==null){
            return false;
        }

        return user.checkPasswordIsCorrect(password);
    }

    public AdminUser get(String userName) {
        return adminUsers.getOrDefault(userName, null);
    }
}
