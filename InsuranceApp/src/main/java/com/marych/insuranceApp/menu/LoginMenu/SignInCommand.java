package com.marych.insuranceApp.menu.LoginMenu;

import com.marych.insuranceApp.menu.mainMenu.MainMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;

import static com.marych.insuranceApp.Main.*;

public class SignInCommand implements MenuItem {
    @Override
    public void execute() throws IOException {
        user = userInfo.login();
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
    }
}
