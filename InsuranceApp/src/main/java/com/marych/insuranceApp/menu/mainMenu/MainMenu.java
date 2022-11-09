package com.marych.insuranceApp.menu.mainMenu;

import com.marych.insuranceApp.menu.insuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.menu.derivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.menu.commonCommands.ExitCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.CommandMenuExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.marych.insuranceApp.Main.user;


public class MainMenu {
    private final Map<String, MenuItem> menuItems;

    public MainMenu() {

        menuItems = new LinkedHashMap<>();
        if (user instanceof Customer) {
            menuItems.put("der", new DerivativeMenu());
            menuItems.put("ins", new InsurancePolicyMenu());
            menuItems.put("exit", new ExitCommand());
        } else if (user instanceof InsuranceSpecialist) {
            menuItems.put("der", new DerivativeMenu());
            menuItems.put("ins", new InsurancePolicyMenu());
            menuItems.put("exit", new ExitCommand());
        }

    }

    public boolean run() {
        showMenuInfo();
        CommandMenuExecutor.execute(menuItems);
        return true;

    }
    private void showMenuInfo(){
        System.out.println("Вітаємо!, " + user.getFirstName());
        System.out.println("*".repeat(60));
        System.out.println();
        System.out.println("der - перейти у меню керування деривативами.");
        System.out.println("ins - перейти у меню керування страховими зобовʼязаннями.");
        System.out.println("exit - вийти із програми");
    }


}