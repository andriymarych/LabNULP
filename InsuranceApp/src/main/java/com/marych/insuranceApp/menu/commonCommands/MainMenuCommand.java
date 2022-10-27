package com.marych.insuranceApp.menu.commonCommands;

import com.marych.insuranceApp.menu.mainMenu.MainMenu;

public class MainMenuCommand implements MenuItem {

    @Override
    public void execute() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
    }
}
