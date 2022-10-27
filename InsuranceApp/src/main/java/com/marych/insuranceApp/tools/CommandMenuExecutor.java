package com.marych.insuranceApp.tools;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class CommandMenuExecutor {

    public static void execute(Map<String, MenuItem> menuItems){
        Scanner in = new Scanner(System.in);
        String command;
        do{
            System.out.println("Введіть команду:");
            command = in.nextLine();
            try {
                menuItems.getOrDefault(command, () -> System.out.println("Невірна команда, спробуйте ще.")).execute();
            }catch (IOException e){
                e.printStackTrace();
            }
        }while(!menuItems.containsKey(command));
    }
}
