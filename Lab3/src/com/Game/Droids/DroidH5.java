package com.Game.Droids;

import java.util.Random;

public class DroidH5 extends Droid {
    private int healPower;
    private static final int typeId = 25002;

    public DroidH5(String name, int level) {
        super(name, 100, level);
        setDamage(15);
        this.healPower = 15;
        if (level == 2) {
            this.setHealth(120);
            this.healPower = 20;
        } else if (level == 3) {
            this.setHealth(130);
            this.healPower = 25;
        }
    }

    @Override
    protected int getHit(int damage) {
        setHealth(this.getHealth() - damage);
        if (getHealth() <= 0) {
            setHealth(0);
        }
        return damage;
    }

    public int getTypeId() {
        return typeId;
    }

    public void heal(Droid otherDroid) {
        boolean healed = false;
        Random random = new Random();
        int startHP;
        int healPoint = 10 + random.nextInt(healPower);
        if (this == otherDroid && this.getHealth() < 100) {
            startHP = this.getHealth();
            this.setHealth(Math.min(this.getHealth() + healPoint, 100));
            System.out.println("\u001B[32mDroid " + this.getName() + " restored his health by " + (this.getHealth() - startHP) + " hp\033[0m");
            healed = true;
        } else if (otherDroid.getHealth() < 100) {
            startHP = otherDroid.getHealth();
            otherDroid.setHealth(Math.min(otherDroid.getHealth() + healPoint, 100));
            System.out.println("\u001B[32mDroid " + this.getName() + " restored " + otherDroid.getName() +
                    "'s health by " + (otherDroid.getHealth() - startHP) + " hp\033[0m");
            healed = true;
        } else if (this.getHealth() + healPoint < 100) {
            startHP = this.getHealth();
            this.setHealth(Math.min(this.getHealth() + healPoint, 100));
            System.out.println("\u001B[32mDroid " + this.getName() + " restored his health by " + (this.getHealth() - startHP)+ " hp\033[0m");
            healed = true;
        }
        if (healed) {
            healPower -= 5;
        }
        if (healPower < 0)
            healPower = 0;
    }

    public int getHealPower() {
        return healPower;
    }

    @Override
    public int attack(Droid otherDroid) {
        return otherDroid.getHit(getDamage());
    }


    @Override
    public String toString() {
        return "Droid(" +
                "Name:'" + getName() +
                "'|health = " + getHealth() +
                "|heal power = " + healPower + ")";
    }

    @Override
    public String toString(boolean coloredPrint) {
        return "\033[0;92mDroid\033[0m(" +
                "Name:'" + getName() +
                "'\u001B[32m |health = " + getHealth() +
                "|\033[0m\u001B[33mheal = " + healPower + "\u001B[0m)";
    }
}
