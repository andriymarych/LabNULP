package com.Game.Arena;

import com.Game.Droids.Droid;
import com.Game.Droids.DroidH5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import static com.Game.Main.sleep;

public class Battle1VS1 extends Arena {
    private final Droid firstDroid;
    private final Droid secondDroid;
    Droid winner;

    public Battle1VS1(Droid firstDroid, Droid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;

    }

    public void printBattleInfo() {
        System.out.println("\033[47m \033[1;30m" + " ".repeat(14) + " Battle Information" + " ".repeat(16) + "\033[0m\n");
        System.out.println(" ".repeat(19) + "First Droid" + " ".repeat(18));
        System.out.println("Droid type: " + firstDroid.getClass().getSimpleName());
        System.out.println(firstDroid.toString(true) + "\033[0;33m Level : " + firstDroid.getLevel() + "\033[0m\n");
        System.out.println("-".repeat(50));
        System.out.println(" ".repeat(18) + "Second Droid" + " ".repeat(19));
        System.out.println("Droid type: " + secondDroid.getClass().getSimpleName());
        System.out.println(secondDroid.toString(true) + "\033[0;33m Level : " + firstDroid.getLevel() + "\033[0m\n");
        System.out.println();
    }

    public void fight() {
        System.out.println(" ".repeat(33) + "\033[47m\033[1;30m The battle 1 VS 1 begins  \033[0m");
        do {
            round++;
            changeSide();
            damage = attacker.attack(defender);
            printFightResult();
        } while (defender.isAlive());
        this.winner = attacker;
        printBattleResult();
    }

    public void fight(BufferedWriter writer) throws IOException {
        System.out.println(" ".repeat(33) + "\033[47m\033[1;30m The battle 1 VS 1 begins  \033[0m");
        writer.write(" ".repeat(33) + "The battle 1 VS 1 begins\n");
        do {
            round++;
            changeSide();
            damage = attacker.attack(defender);
            printFightResult(writer);
        } while (defender.isAlive());
        this.winner = attacker;
        printBattleResult(writer);
        writer.close();
    }

    private void printFightResult() {
        System.out.println("\u001B[33m" + "-".repeat(100) + "\033[0m");
        System.out.println(" ".repeat(42) + "\033[47m\033[1;30m " + "Round " + round + " \033[0m");
        if (defender.isAlive()) {
            System.out.println(defender.toString(true) + " was hit by "
                    + attacker.getName() + "\u001B[31m ( Damage : " + damage + " )\033[0m");
        } else {
            System.out.print("\033[0;31m" + "Droid \033[0m" + defender.getName() + "\033[0;31m was destroyed by " + "\033[0m");
            System.out.print(attacker.getName() + "\033[0;31m!!!\033[0m\n");
        }
        sleep(1);

    }

    private void printFightResult(BufferedWriter writer) throws IOException {
        printFightResult();
        writer.write("-".repeat(100) + "\n");
        writer.write(" ".repeat(42) + "Round " + round + "\n");
        if (defender.isAlive()) {
            writer.write(defender.toString() + " was hit by "
                    + attacker.getName() + " ( Damage : " + damage + " )\n");
        } else {
            writer.write("Droid " + defender.getName() + " was destroyed by ");
            writer.write(attacker.getName() + "!!!\n");
        }
    }

    public void printBattleResult() {
        System.out.println("-".repeat(100));
        System.out.println(" ".repeat(38) + "\033[47m\033[1;30m " + "End of the game " + "\033[0m\n");
        System.out.println("\033[0;30m\033[43mThe winner of the 1v1 battle - \"" + winner.getName() +
                "\" ( Droid model : " + attacker.getClass().getSimpleName() + " )\033[0m");

    }

    public void printBattleResult(BufferedWriter writer) throws IOException {
        printBattleResult();
        writer.write("-".repeat(100) + "\n");
        writer.write("The winner of the 1v1 battle - " + winner.getName() +
                "( Droid model : " + attacker.getClass().getSimpleName() + "\n");
    }

    private void changeSide() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = secondDroid;
            defender = firstDroid;
        } else {
            attacker = firstDroid;
            defender = secondDroid;
        }
        if (attacker instanceof DroidH5 ) {
            if(((DroidH5) attacker).getHealPower() > 0)
                ((DroidH5) attacker).heal(attacker);
        }
    }
}
