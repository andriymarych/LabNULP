package com.Game.Droids;


public abstract class Droid {
    private final String name;
    private int health;
    private int damage;

    private final int level;

    private static final int typeId = 25000;

    public Droid(String name, int health, int damage, int level){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.level = level;
    }
    public Droid(String name, int health, int level){
        this(name, health, 0,level);
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getLevel(){
        return this.level;
    }

    protected void setDamage(int damage){
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }


    protected abstract int getHit(int damage);
    public int getDamage(){
        return damage;
    }
    public Boolean isAlive(){
        return health > 0;
    }
    public abstract int attack(Droid otherDroid);
    @Override
    public abstract  String toString();
    public abstract String toString(boolean coloredPrint);

}
