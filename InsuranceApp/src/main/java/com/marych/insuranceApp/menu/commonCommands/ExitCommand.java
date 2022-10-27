package com.marych.insuranceApp.menu.commonCommands;

public class ExitCommand implements MenuItem {


    @Override
    public void execute() {
        System.exit(0);
    }
}
