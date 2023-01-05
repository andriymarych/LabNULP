package com.marych.insuranceApp.user;

public class  UserSession {
    private static UserSession instance;
    private String login;
    private int diiaId;
    private int UserId;

    private UserSession(String userName) {
        this.login = userName;

    }
    public static void createInstance(String login) {
        if(instance == null) {
            instance = new UserSession(login);
        }
    }
    public static UserSession getInstance() {
        return instance;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getUserId() {
        return UserId;
    }

    public String getLogin() {
        return login;
    }

}
