package com.marych.insuranceApp.menu.insuranceMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.menu.insuranceMenu.createInsurance.CreateInsCommand;
import com.marych.insuranceApp.menu.commonCommands.MainMenuCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.CommandMenuExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

public class InsurancePolicyMenu implements MenuItem {

    private final Map<String, MenuItem> menuItems;
    User user;

    public InsurancePolicyMenu() {
        user = getUser();
        menuItems = new LinkedHashMap<>();
        if (user instanceof Customer) {
            menuItems.put("create ins", new CreateInsCommand());
            menuItems.put("show ins", new ShowInsCommand());
            menuItems.put("del ins", new DeleteInsCommand());
            menuItems.put("exit", new MainMenuCommand());
        } else if (user instanceof InsuranceSpecialist) {
            menuItems.put("create ins", new CreateInsCommand());
            menuItems.put("show ins", new ShowInsCommand());
            menuItems.put("del ins", new DeleteInsCommand());
            menuItems.put("exit", new MainMenuCommand());
        }
    }

    @Override
    public boolean execute() {
        printInfo();
        CommandMenuExecutor.execute(menuItems);
        return true;
    }
    private void printInfo(){
        System.out.println("*".repeat(60));
        System.out.println();
        if (user instanceof Customer) {
            System.out.println("create ins - створити договір страхування.");
            System.out.println("show ins - переглянути створені договори страхування.");
            System.out.println("del ins - видалити існуючий договір страхування.");
            System.out.println("exit - повернутися у головне меню.");
        } else if (user instanceof InsuranceSpecialist) {
            System.out.println("create ins - створити договір страхування.");
            System.out.println("show ins - переглянути створені договори страхування.");
            System.out.println("del ins - видалити існуючий договір страхування.");
            System.out.println("exit - повернутися у головне меню.");
        }
    }
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        if (Main.user != null) {
            return Main.user;
        } else {
            return user;
        }
    }
}
