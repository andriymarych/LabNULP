package com.Game.Arena;

import com.Game.Droids.Droid;

import java.util.ArrayList;

public class DroidTeam {

    String teamName;
    ArrayList<Droid> droidsTeam;

    ArrayList<Droid> destroyedDroids;
    int numberOfDroids;

    public DroidTeam(String teamName, ArrayList<Droid> DroidsTeam) {
        this.teamName = teamName;
        this.droidsTeam = DroidsTeam;
        this.numberOfDroids = droidsTeam.size();
        this.destroyedDroids = new ArrayList<>();
    }

    public boolean isAlive() {
        return droidsTeam.size() >= 1;
    }

    public void teamInfo() {
        System.out.println("\033[47m \033[1;30m " + " ".repeat(15) + "\"" + getTeamName() + "\" Team :" +
                " ".repeat(33 - (getTeamName().length() + 9)) + "\033[0m\n");
        for (int i = 0; i <  droidsTeam.size(); i++) {
            System.out.println("Droid type : " + droidsTeam.get(i).getClass().getSimpleName());
            System.out.println(droidsTeam.get(i).toString(true));
            if(i != droidsTeam.size()-1)
                System.out.println("-".repeat(50));
        }
    }

    public String getAliveDroids() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Droid droid : droidsTeam) {
            if (droid.isAlive())
                stringBuilder.append(droid.getName()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public void destroyed(Droid destroyedDroid) {
        destroyedDroids.add(destroyedDroid);
        droidsTeam.remove(destroyedDroid);
    }

    public Droid getDroid(int index) {
        return droidsTeam.get(index);
    }

    public int size() {
        return droidsTeam.size();
    }

    public String getTeamName() {
        return teamName;
    }
}
