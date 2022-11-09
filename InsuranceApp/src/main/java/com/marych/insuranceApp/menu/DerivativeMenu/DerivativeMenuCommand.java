package com.marych.insuranceApp.menu.derivativeMenu;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;
import java.util.Objects;

public class DerivativeMenuCommand implements MenuItem {

    DerivativeMenu derivativeMenu;
    @Override
    public boolean execute() throws IOException {
        derivativeMenu = getDerivativeMenu();
        derivativeMenu.execute();
        return true;
    }

    public void setDerivativeMenu(DerivativeMenu derivativeMenu) {
        this.derivativeMenu = derivativeMenu;
    }

    public DerivativeMenu getDerivativeMenu() {
        return Objects.requireNonNullElseGet(derivativeMenu, DerivativeMenu::new);
    }
}
