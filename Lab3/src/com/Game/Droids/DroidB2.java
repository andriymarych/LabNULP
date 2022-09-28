package com.Game.Droids;

import java.util.Random;

public class DroidB2 extends Droid {
    private int armor;
    private int regeneration;
    private static final int typeId = 25001;

    public DroidB2(String name, int level) {
        super(name,110,level);
        setDamage(27);
        if(level == 2) {
            setDamage(35);
            armor = 10;
            regeneration = 10;
        } else if(level == 3) {
            setDamage(40);
            armor = 40;
            regeneration = 30;
        }
    }

    @Override
    protected int getHit(int damage) {
        Random random = new Random();
        int healthAndArmorBefore = this.getHealth() + armor;
        if(armor <= 0) {
            setHealth(getHealth() - damage);
            if(getHealth() <= 0) {
                setHealth(0);
                return healthAndArmorBefore - this.getHealth() + armor;
            }
            if(random.nextBoolean()) {
                int reg = (int) (getHealth() + regeneration * random.nextFloat());
                setHealth(reg);
            }
        }else if(armor - damage >= 0){
            armor -= damage;
        }else{
            damage -=armor;
            armor = 0;
            setHealth(getHealth() - damage);
            if(getHealth() <= 0) {
                setHealth(0);
                return healthAndArmorBefore - this.getHealth() + armor;
            }
            if(random.nextBoolean())
                setHealth((int)(getHealth() + regeneration * (0.5+ random.nextFloat())));
        }
        return healthAndArmorBefore - this.getHealth() + armor;
    }

    @Override
    public int attack(Droid otherDroid) {
        return otherDroid.getHit(getDamage());
    }

    public int getTypeId(){
        return typeId;
    }

    @Override
    public String toString() {
        return "Droid(" +
                "Name:'" + getName() +
                "'|health = " + getHealth() +
                "|armor = " + armor + ")";
    }

    @Override
    public String toString(boolean coloredPrint) {
        return "\033[0;92mDroid\033[0m(" +
                "Name:'" + getName() +
                "'\u001B[32m|health = " + getHealth() +
                "|\033[0m\u001B[36marmor = " + armor + "\033[0m)";
    }

}
