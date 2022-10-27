package com.marych.insuranceApp.menu.InsuranceMenu;

import com.marych.insuranceApp.User.*;
import com.marych.insuranceApp.menu.InsuranceMenu.createInsurance.CreateInsCommand;
import com.marych.insuranceApp.menu.commonCommands.MainMenuCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.CommandMenuExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.marych.insuranceApp.Main.*;

public class InsurancePolicyMenu implements MenuItem {

    private final Map<String, MenuItem> menuItems;

    public InsurancePolicyMenu() {
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
    public void execute() {
        printInfo();
        CommandMenuExecutor.execute(menuItems);
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
}
