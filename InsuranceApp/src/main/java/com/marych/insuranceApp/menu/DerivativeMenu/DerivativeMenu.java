package com.marych.insuranceApp.menu.DerivativeMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.menu.DerivativeMenu.showDerCommand.ShowDerCommand;
import com.marych.insuranceApp.menu.commonCommands.MainMenuCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.CommandMenuExecutor;

import java.util.LinkedHashMap;
import java.util.Map;


import static com.marych.insuranceApp.Main.*;

public class DerivativeMenu implements MenuItem {

    private final Map<String, MenuItem> menuItems;

    public DerivativeMenu() {
        menuItems = new LinkedHashMap<>();
        if (user instanceof Customer) {
            menuItems.put("create der", new CreateDerCommand());
            menuItems.put("show der", new ShowDerCommand());
            menuItems.put("del der", new DeleteDerCommand());
            menuItems.put("exit", new MainMenuCommand());
        } else if (user instanceof InsuranceSpecialist) {
            menuItems.put("create der", new CreateDerCommand());
            menuItems.put("show der", new ShowDerCommand());
            menuItems.put("del der", new DeleteDerCommand());
            menuItems.put("conf der", new ConfirmationCommand());
            menuItems.put("exit", new MainMenuCommand());
        }

    }

    @Override
    public void execute() {
        printMenuInfo();
        CommandMenuExecutor.execute(menuItems);
    }
    private void printMenuInfo(){
        System.out.println("*".repeat(60));
        System.out.println();
        if (user instanceof Customer) {
            System.out.println("create der - створити дериватив.");
            System.out.println("show der - переглянути створені деривативи.");
            System.out.println("del der - видалити дериватив.");
            System.out.println("exit - повернутися у головне меню.");
        } else if (user instanceof InsuranceSpecialist) {
            System.out.println("create der - створити дериватив.");
            System.out.println("show der - переглянути створені деривативи.");
            System.out.println("delete der - видалити дериватив.");
            System.out.println("conf der - переглянути деривативи, які перебувають у статусі підтвердження");
            System.out.println("exit - повернутися у головне меню.");
        }
    }
}
