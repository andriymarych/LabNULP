package com.Game.Arena;

import com.Game.Droids.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import static com.Game.Main.*;

public class BattleTeams extends Arena {

    DroidTeam firstTeam;
    DroidTeam secondTeam;
    private DroidTeam attackingTeam;
    private DroidTeam defendingTeam;
    private DroidTeam winningTeam;


    public BattleTeams(DroidTeam firstTeam, DroidTeam secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void printBattleInfo() {

        System.out.println("\033[47m \033[1;30m" + " ".repeat(14) + " Battle Information" + " ".repeat(16) + "\033[0m\n");
        firstTeam.teamInfo();
        System.out.println();
        secondTeam.teamInfo();
    }

    public void fight() {
        System.out.println(" ".repeat(57) + "\033[47m\033[1;30m The battle begins  \033[0m");
        do {
            changeTeamSide();
            round++;
            changeTeamSide();
            selectDroid();
            damage = attacker.attack(defender);
            if (!defender.isAlive()) {
                defendingTeam.destroyed(defender);
            }
            printFightResult();
        } while (defendingTeam.isAlive());
        winningTeam = attackingTeam;
        printBattleResult();

    }

    public void fight(BufferedWriter writer) throws IOException {
        System.out.println("\033[47m\033[1;30m" + " ".repeat(52) + " The battle of the teams begins "
                + " ".repeat(46) + "\033[0m");
        writer.write(" ".repeat(40) + "The battle of the teams begins \n");
        do {
            round++;
            changeTeamSide();
            selectDroid();
            damage = attacker.attack(defender);
            if (!defender.isAlive()) {
                defendingTeam.destroyed(defender);
            }
            printFightResult(writer);
        } while (defendingTeam.isAlive());
        winningTeam = attackingTeam;
        printBattleResult(writer);
        writer.close();
    }

    private void changeTeamSide() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attackingTeam = secondTeam;
            defendingTeam = firstTeam;
        } else {
            attackingTeam = firstTeam;
            defendingTeam = secondTeam;
        }
    }

    private void printFightResult() {
        System.out.println("\u001B[33m" + "-".repeat(130) + "\033[0m");
        System.out.println(" ".repeat(62) + "\033[47m\033[1;30m " + "Round " + round + " \033[0m");
        if (defender.isAlive()) {
            if(damage < 0)
                damage = 0;
            System.out.println(defender.toString(true) + " from the \"" + defendingTeam.getTeamName()
                    + "\" team was hit by "
                    + attacker.getName() + " from the \"" +
                    attackingTeam.getTeamName() + "\" team \u001B[31m( Damage : " + damage + " )\033[0m");
        } else {
            System.out.print("\033[0;31mDroid \033[0m" + defender.getName() + " \033[0;31m" + "from the" +
                    " \033[0m \"" + defendingTeam.getTeamName()
                    + "\" \033[0;31mteam  was destroyed by\033[0m ");
            System.out.print(attacker.getName() + "\033[0;31m!!!\033[0m\n");
        }
        sleep(1);

    }

    private void printFightResult(BufferedWriter writer) throws IOException {
        printFightResult();
        writer.write("-".repeat(120) + "\n");
        writer.write(" ".repeat(52) + "Round " + round + "\n");
        if (defender.isAlive()) {
            if(damage < 0)
                damage = 0;
            writer.write(defender.toString() + " from the \"" + defendingTeam.getTeamName()
                    + "\" team was hit by "
                    + attacker.getName() + " from the \"" +
                    attackingTeam.getTeamName() + "\" team ( Damage : " + damage + " )\n");
        } else {
            writer.write("Droid " + defender.getName() + " from the \"" + defendingTeam.getTeamName()
                    + "\" team  was destroyed by ");
            writer.write(attacker.getName() + "!!!\n");
        }
    }

    public void printBattleResult() {
        System.out.println("\033[47m\033[1;30m" + " ".repeat(130) + "\033[0m\n");
        System.out.print("\n\033[0;30m\033[43m" + " ".repeat(44) + "The winner of the team battles is \"" + winningTeam.getTeamName()
                + "\"  team");
        System.out.print(" ".repeat(43) + "\033[0m\n");
        System.out.println("\033[0;92m" + "Droids, that remained alive from the \"" + winningTeam.getTeamName()
                + "\" team: \033[0m" + winningTeam.getAliveDroids());
    }

    public void printBattleResult(BufferedWriter writer) throws IOException {
        printBattleResult();
        writer.write("-".repeat(120) + "\n\n");
        writer.write("The winner of the team battles is \"" + winningTeam.getTeamName() + "\"  team");
        writer.write("Droids, that remained alive from the \"" + winningTeam.getTeamName()
                + "\" team : " + winningTeam.getAliveDroids());
    }

    private void selectDroid() {
        Random random = new Random();
        int attackingDroidIndex = random.nextInt(attackingTeam.size());
        int defendingDroidIndex = random.nextInt(defendingTeam.size());
        this.attacker = attackingTeam.getDroid(attackingDroidIndex);
        this.defender = defendingTeam.getDroid(defendingDroidIndex);
        if (attacker instanceof DroidH5) {
            if (((DroidH5) attacker).getHealPower() > 0) {
                int restoredDroidIndex;
                Droid restoredDroid;
                do {
                    restoredDroidIndex = random.nextInt(attackingTeam.size());
                    restoredDroid = attackingTeam.getDroid(restoredDroidIndex);
                }
                while (attackingTeam.size() > 1 && restoredDroid == attacker);
                ((DroidH5) attacker).heal(restoredDroid);
            }
        }

    }
}
