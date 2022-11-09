package com.marych.insuranceApp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.tools.LogStatus;


public abstract class User {
    @JsonIgnore
    LogStatus logStatus;
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("userRole")
    private int userRole;
    @JsonProperty("password")
    String password;
    @JsonProperty("login")
    private String login;


    public User() {

    }


    public String getPassword() {
        return password;
    }

    public void setLogStatus(LogStatus logStatus) {
        this.logStatus = logStatus;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getUserRole() {
        return userRole;
    }
    public String getLogin(){
        return login;
    }
    public abstract String getFirstName();
    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }
    public LogStatus getLogStatus(){
        return logStatus;
    }

    public int getUserId() {
        return userId;
    }
}
