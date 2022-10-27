package com.marych.insuranceApp.menu.LoginMenu;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.mainMenu.MainMenu;

import java.io.IOException;

import static com.marych.insuranceApp.Main.*;

public class SignUpCommand implements MenuItem {
    @Override
    public void execute() throws IOException {
        user = userInfo.userCreate();
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
    }
}
