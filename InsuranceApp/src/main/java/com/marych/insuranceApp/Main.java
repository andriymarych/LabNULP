package com.marych.insuranceApp;


import com.marych.insuranceApp.DiiaGov.DiiaGovList;
import com.marych.insuranceApp.User.User;
import com.marych.insuranceApp.User.UserList;
import com.marych.insuranceApp.insurance.InsuranceCompanyList;
import com.marych.insuranceApp.menu.LoginMenu.LoginMenu;



import java.io.IOException;


public class Main {
    public static User user;
    public static UserList userInfo = new UserList();
    public static DiiaGovList diiaGovDocuments = new DiiaGovList();
    public static InsuranceCompanyList insuranceCompanyList = new InsuranceCompanyList();


    public static void main(String[] args) throws IOException {

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.execute();
    }
}
