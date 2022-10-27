package com.marych.insuranceApp.menu.mainMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.menu.DerivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.menu.commonCommands.ExitCommand;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.CommandMenuExecutor;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class MainMenu {
    private final Map<String, MenuItem> menuItems;

    public MainMenu() {

        menuItems = new LinkedHashMap<>();
        if (user instanceof Customer) {
            menuItems.put("der", new DerivativeMenu());
            menuItems.put("ins", new InsurancePolicyMenu());
            menuItems.put("set", new SettingsCommand());
            menuItems.put("exit", new ExitCommand());
        } else if (user instanceof InsuranceSpecialist) {
            menuItems.put("der", new DerivativeMenu());
            menuItems.put("ins", new InsurancePolicyMenu());
            menuItems.put("set", new SettingsCommand());
            menuItems.put("exit", new ExitCommand());
        }

    }

    public void run() {
        if (user == null) {
            System.exit(0);
        }
        showMenuInfo();
        CommandMenuExecutor.execute(menuItems);

    }
    private void showMenuInfo(){
        System.out.println("*".repeat(60));
        System.out.println();
        System.out.println("der - перейти у меню керування деривативами.");
        System.out.println("ins - перейти у меню керування страховими зобовʼязаннями.");
        System.out.println("exit - вийти із програми");
    }


}