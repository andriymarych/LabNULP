package com.Game.Menu;

import com.Game.Arena.Battle1VS1;
import com.Game.Arena.BattleTeams;
import com.Game.Arena.DroidTeam;
import com.Game.Droids.Droid;
import com.Game.Droids.DroidSpawn;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static com.Game.Main.sleep;

public class Menu {

    private int option = -1;
    private Battle1VS1 battle1VS1;
    private BattleTeams battleTeams;
    private BufferedWriter writer;

    public Menu() {
    }

    private void printHeader() {
        System.out.println("\033[47m\033[1;30m " + " ".repeat(45) + "DroidGame" + " ".repeat(45) + " \033[0m");
        System.out.println();
    }

    public void runMenu() throws IOException {
        printHeader();
        mainMenu();
    }

    private void mainMenu() throws IOException {
        Scanner in = new Scanner(System.in);
        int option = -1;
        System.out.println("\033[47m\033[1;30m" + " Select option:" + " ".repeat(36) + "\033[0m\n");
        System.out.println("\033[47m\033[1;30m" + "1." + "\033[0m" + " Start Battle 1VS1");
        System.out.println("\033[47m\033[1;30m" + "2." + "\033[0m" + " Start Battle Team");
        System.out.println("\033[47m\033[1;30m" + "3." + "\033[0m" + " Play the battle from a text file");
        System.out.println("\033[47m\033[1;30m" + "0." + "\033[0m" + " Exit Game");
        while (option < 0 || option > 3) {
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }
        switch (option) {
            case 1 -> battle1VS1Menu();
            case 2 -> battleTeamsMenu();
            case 3 -> playBattleFile();
            case 0 -> System.exit(0);
        }
    }

    private void playBattleFile() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the file path:");
        String filePath = in.nextLine();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                sleep(1);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectPrintTo() {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose the way to display the result of the battle:");
        System.out.println("\033[47m\033[1;30m" + "1." + "\033[0m" + " Console");
        System.out.println("\033[47m\033[1;30m" + "2." + "\033[0m" + " File");
        int printMode = -1;
        while (printMode < 1 || printMode > 2) {
            try {
                printMode = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }
        if (printMode == 2) {
            System.out.println("Enter the file path:");
            String filePath = in.nextLine();
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                writer = new BufferedWriter(fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void battle1VS1Menu() throws IOException {
        Scanner in = new Scanner(System.in);
        ArrayList<Droid> droidArrayList = new ArrayList<>();
        option = -1;
        int numberOfDroids = 1;
        System.out.println("\033[47m\033[1;30m" + " ".repeat(50) + "\033[0m\n");
        System.out.println("\033[47m\033[1;30m" + "1." + "\033[0m" + " Create Droids");
        System.out.println("\033[47m\033[1;30m" + "0." + "\033[0m" + " Return to the main menu");
        while (option < 0 || option > 1) {
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }
        switch (option) {
            case 1 -> {
                while (numberOfDroids <= 2) {
                    System.out.println("\033[47m\033[1;30m" + " ".repeat(50) + "\033[0m\n");
                    System.out.println("\n\033[47m\033[1;30m" + "-".repeat(50) + "\033[0m\n");
                    System.out.println("Enter the details of " + numberOfDroids + " droid\n");
                    droidArrayList.add(DroidSpawn.spawn());
                    numberOfDroids++;
                }
                battle1VS1 = new Battle1VS1(droidArrayList.get(0), droidArrayList.get(1));
            }
            case 0 -> mainMenu();
        }
        option = -1;
        selectPrintTo();
        while(true) {
            battleRunMenu();
            switch (option) {
                case 1 -> {
                    if (writer != null) {
                        battle1VS1.fight(writer);
                    } else {
                        battle1VS1.fight();
                    }
                    System.exit(0);
                }
                case 2 -> battle1VS1.printBattleInfo();
                case 0 -> mainMenu();
            }
        }
    }

    private void battleRunMenu() {
        option = -1;
        Scanner in = new Scanner(System.in);
        System.out.println("\033[47m\033[1;30m" + " ".repeat(50) + "\033[0m\n");
        System.out.println("Do you want to start a battle?");
        System.out.println("\033[47m\033[1;30m" + "1." + "\033[0m" + " Start battle");
        System.out.println("\033[47m\033[1;30m" + "2." + "\033[0m" + " Show droids information");
        System.out.println("\033[47m\033[1;30m" + "0." + "\033[0m" + " Return to the main menu");
        while (option < 0 || option > 2) {
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }

    }

    private void battleRunTeamsMenu() {
        option = -1;
        Scanner in = new Scanner(System.in);
        System.out.println("\n\033[47m\033[1;30m" + "-".repeat(50) + "\033[0m\n");
        System.out.println("Do you want to start a battle?");
        System.out.println("\033[47m\033[1;30m" + "1." + "\033[0m" + " Start battle");
        System.out.println("\033[47m\033[1;30m" + "2." + "\033[0m" + " Show teams information");
        System.out.println("\033[47m\033[1;30m" + "0." + "\033[0m" + " Return to the main menu");
        while (option < 0 || option > 2) {
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }
    }

    private void battleTeamsMenu() throws IOException {
        Scanner in = new Scanner(System.in);
        option = -1;
        System.out.println("1.Create Teams");
        System.out.println("0.Return to the main menu");
        while (option < 0 || option > 1) {
            try {
                option = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }
        switch (option) {
            case 1 -> {
                DroidTeam firstTeam = DroidSpawn.teamSpawn();
                DroidTeam secondTeam = DroidSpawn.teamSpawn();
                battleTeams = new BattleTeams(firstTeam, secondTeam);
            }
            case 0 -> mainMenu();
        }

        selectPrintTo();
        while (true) {
            battleRunTeamsMenu();
            switch (option) {
                case 1 -> {
                    assert battleTeams != null;
                    if (writer != null) {
                        battleTeams.fight(writer);
                    } else
                        battleTeams.fight();
                    System.exit(0);
                }
                case 2 -> battleTeams.printBattleInfo();
                case 0 -> mainMenu();
            }
        }
    }
}
