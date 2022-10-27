package com.marych.insuranceApp.menu.DerivativeMenu;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;

public class DerivativeMenuCommand implements MenuItem {
    @Override
    public void execute() throws IOException {
        DerivativeMenu derivativeMenu = new DerivativeMenu();
        derivativeMenu.execute();
    }
}
