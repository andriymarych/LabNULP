package com.marych.insuranceApp.menu.loginMenu;

import com.marych.insuranceApp.menu.CommandMenuExecutor;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginMenu {
    private final Map<String, MenuItem> menuItems;

    public LoginMenu(){
        JsonScanner.getData();
        menuItems = new LinkedHashMap<>();
        menuItems.put("sign in", new SignInCommand());
        menuItems.put("sign up", new SignUpCommand());

    }

    public boolean execute() throws IOException {
        showMenuInfo();
        CommandMenuExecutor.execute(menuItems);
        return true;
    }
    private void showMenuInfo(){
        System.out.println("Welcome to Insurance App");
        System.out.println("sign in - увійти.");
        System.out.println("sign up - зареєструватися.");
    }
}
