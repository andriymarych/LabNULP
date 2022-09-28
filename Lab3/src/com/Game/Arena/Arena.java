package com.Game.Arena;

import com.Game.Droids.Droid;

public abstract class Arena {
    Droid attacker;
    Droid defender;
    int round;
    int damage;

    public String getName(){
        return attacker.getName();
    }
    abstract void  fight();
    abstract void printBattleResult();
}
