package com.marych.insuranceApp.menu.derivativeMenu.showDerCommand;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.menu.CommandMenuExecutor;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.derivativeMenu.DerivativeMenuCommand;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShowDerCommand implements MenuItem {
    private final Map<String, MenuItem> menuItems;

    public ShowDerCommand() {
        menuItems = new LinkedHashMap<>();
        if (Main.user instanceof Customer) {
            menuItems.put("show all", new ShowAllDerivativesCommand());
            menuItems.put("show der", new ShowFilteredDerivativeCommand());
            menuItems.put("exit", new DerivativeMenuCommand());
        } else if (Main.user instanceof InsuranceSpecialist) {
            menuItems.put("show all", new ShowAllDerivativesCommand());
            menuItems.put("show der", new ShowFilteredDerivativeCommand());
            menuItems.put("exit", new DerivativeMenuCommand());
        }
    }

    @Override
    public boolean execute() {
        System.out.println("*".repeat(60));
        System.out.println();
        if (Main.user instanceof Customer) {
            System.out.println("show all  - переглянути усі створені деривативи.");
            System.out.println("show der - відфільтрувати та переглянути деривативи.");
            System.out.println("exit - повернутися у меню керування деривативами.");
        } else if (Main.user instanceof InsuranceSpecialist) {
            System.out.println("show all  - переглянути усі створені деривативи.");
            System.out.println("show der - відфільтрувати та переглянути деривативи.");
            System.out.println("exit - повернутися у меню керування деривативами.");
        }
        CommandMenuExecutor.execute(menuItems);
        return true;
    }
}
