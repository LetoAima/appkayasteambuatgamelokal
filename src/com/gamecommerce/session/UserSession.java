package com.gamecommerce.session;

import com.gamecommerce.model.User;

public class UserSession {
    private static UserSession instance;
    private User user;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) instance = new UserSession();
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return user.getId();
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void clear() {
        user = null;
    }
}