package com.marych.insuranceApp.menu.DerivativeMenu.showDerCommand;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.menu.DerivativeMenu.DerivativeMenuCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.CommandMenuExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.marych.insuranceApp.Main.user;

public class ShowDerCommand implements MenuItem {
    private final Map<String, MenuItem> menuItems;

    public ShowDerCommand() {
        menuItems = new LinkedHashMap<>();
        if (user instanceof Customer) {
            menuItems.put("show all", new ShowAllDerivativesCommand());
            menuItems.put("show der", new ShowFilteredDerivativeCommand());
            menuItems.put("exit", new DerivativeMenuCommand());
        } else if (user instanceof InsuranceSpecialist) {
            menuItems.put("show all", new ShowAllDerivativesCommand());
            menuItems.put("show der", new ShowFilteredDerivativeCommand());
            menuItems.put("exit", new DerivativeMenuCommand());
        }
    }

    @Override
    public void execute() {
        System.out.println("*".repeat(60));
        System.out.println();
        if (user instanceof Customer) {
            System.out.println("show all  - переглянути усі створені деривативи.");
            System.out.println("show der - відфільтрувати та переглянути деривативи.");
            System.out.println("exit - повернутися у меню керування деривативами.");
        } else if (user instanceof InsuranceSpecialist) {
            System.out.println("show all  - переглянути усі створені деривативи.");
            System.out.println("show der - відфільтрувати та переглянути деривативи.");
            System.out.println("exit - повернутися у меню керування деривативами.");
        }
        CommandMenuExecutor.execute(menuItems);
    }
}
