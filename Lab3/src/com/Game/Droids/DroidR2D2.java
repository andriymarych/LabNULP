package com.Game.Droids;

import java.util.Random;

public class DroidR2D2 extends Droid{
    private int shield;
    private int energy;

    private static final int typeId = 25003;

    public DroidR2D2(String name, int level) {
        super(name,100,level);
        setDamage(33);
        if(level == 2 ) {
            setDamage(40);
            shield = 5;
            energy = 5;
        } else if(level == 3) {
            shield = 10;
            energy = 10;
        }
    }

    @Override
    protected int getHit(int damage) {
        Random random = new Random();
        int healthAndShieldBefore = this.getHealth() + this.shield;
        if(shield <= 0) {
            setHealth(getHealth() - damage);
            if(getHealth() <= 0) {
                setHealth(0);
                return healthAndShieldBefore - this.getHealth() + this.shield;
            }
        }else if(shield - damage >= 0){
            shield -= damage;

        }else{
            damage -= shield;
            shield = 0;
            setHealth(getHealth() - damage);
            if(getHealth() <= 0) {
                setHealth(0);
                return healthAndShieldBefore - this.getHealth() + this.shield;
            }
            if(random.nextBoolean())
                energy -= random.nextInt(3);
        }
        return healthAndShieldBefore - this.getHealth() + this.shield;
    }

    public int getTypeId(){
        return typeId;
    }

    @Override
    public int attack(Droid otherDroid) {
        Random random = new Random();
        if(energy >0)
            setDamage((int) (getDamage() * (1 + energy * 0.03)));
        int actualDamage = otherDroid.getHit(getDamage());
        energy-= random.nextInt(2);
        if(energy < 0)
            energy = 0;
        return actualDamage;
    }

    @Override
    public String toString() {
        return "Droid(" +
                "Name:'" + getName() +
                "'|health = " + getHealth() +
                "|shield = " + shield + ")";
    }

    @Override
    public String toString(boolean coloredPrint) {
        return "\033[0;92mDroid\033[0m(" +
                "Name:'" + getName() +
                "'\u001B[32m|health = " + getHealth() +
                "|\033[0m\u001B[36mshield = " + shield + "\033[0m)";
    }
}
