package com.marych.insuranceApp.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class User {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("userRole")
    private int userRole;
    @JsonProperty("password")
    String password;
    @JsonProperty("login")
    private String login;

    public String getPassword() {
        return password;
    }



    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String login, String password){
        this.login = login;
        this.password = password;
    }
    public User(){

    }

    public int getUserRole() {
        return userRole;
    }
    public String getLogin(){
        return login;
    }
    abstract String getFirstName();
    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }

    public int getUserId() {
        return userId;
    }
}
