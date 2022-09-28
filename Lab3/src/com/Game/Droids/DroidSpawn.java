package com.Game.Droids;

import com.Game.Arena.DroidTeam;

import java.util.ArrayList;
import java.util.Scanner;

public class DroidSpawn {
    DroidSpawn() {

    }

    public static Droid spawn() {
        Scanner in = new Scanner(System.in);
        int option = -1;
        int level = 0;
        String droidName;
        System.out.println("\033[47m\033[1;30m " + "Select Droid type:" + " ".repeat(30) + " \033[0m");
        System.out.println("Droid Type | (Properties)\n");
        System.out.println("\033[47m\033[1;30m" + "1." +"\033[0m" +  " Droid B2 | ( Armor, Regeneration )");
        System.out.println("\033[47m\033[1;30m" + "2." +"\033[0m" +" Droid H5 | ( Heal )");
        System.out.println( "\033[47m\033[1;30m" + "3." +"\033[0m" +" Droid R2D2 | ( Shield, Energy )");
        System.out.println("\033[47m\033[1;30m" + "0." +"\033[0m" + " Return to the Battle 1 VS 1 menu");
        while (option < 0 || option > 3) {
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }
        System.out.println("-".repeat(50));
        System.out.println("Enter a droid name");
        droidName = in.nextLine();
        System.out.println("-".repeat(50));
        System.out.println("Select Droid level from 1 to 3:");
        do {
            level = in.nextInt();
            if (level < 0 || level > 3) {
                System.out.println("You entered the wrong droid level. Please try again:");
            }
        } while (level < 0 || level > 3);
        switch (option) {
            case 1:
                return new DroidB2(droidName, level);
            case 2:
                return new DroidH5(droidName, level);
            case 3:
                return new DroidR2D2(droidName, level);
            default:
                return null;
        }
    }

    public static DroidTeam teamSpawn() {
        Scanner in = new Scanner(System.in);
        ArrayList<Droid> droidArrayList = new ArrayList<>();
        int option = -1;
        int numberOfDroids;
        int level = 0;
        String droidName;
        String teamName;
        System.out.println("\n\033[47m\033[1;30m" + "-".repeat(50) + "\033[0m\n" );
        System.out.println("Enter a team name:");
        teamName = in.nextLine();
        System.out.println("Enter the number of team droids:");
        do {
            numberOfDroids = in.nextInt();
            if (numberOfDroids < 0 || numberOfDroids > 10) {
                System.out.println("You have entered an incorrect number. ");
                System.out.println("Enter the correct number of droids between 1 and 10");
            }
        } while (numberOfDroids < 1 || numberOfDroids > 10);
        System.out.println("-".repeat(50));
        for (int i = 1; i <= numberOfDroids; i++) {
            System.out.println("Enter the data of " + i + " droid");
            droidArrayList.add(DroidSpawn.spawn());
        }
        return new DroidTeam(teamName, droidArrayList);
    }
}
