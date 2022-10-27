package com.marych.insuranceApp.menu.LoginMenu;

import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.CommandMenuExecutor;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu {
    private final Map<String, MenuItem> menuItems;

    public LoginMenu(){
        JsonScanner.getData();
        menuItems = new LinkedHashMap<>();
        menuItems.put("sign in", new SignInCommand());
        menuItems.put("sign up", new SignUpCommand());

    }

    public void execute() throws IOException {
        showMenuInfo();
        CommandMenuExecutor.execute(menuItems);
    }
    private void showMenuInfo(){
        System.out.println("Welcome to Insurance App");
        System.out.println("sign in - увійти.");
        System.out.println("sign up - зареєструватися.");
    }
}
