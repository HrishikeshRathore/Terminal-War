package com.company;

public class Card {

    private int id;
    private String name;
    private int price;
    private int damage;
    private int lifePoints;

    public Card(int id, String name) {
        this.id = id;
        this.name = name;
        this.damage = Utils.getRandomNumber(30,50);
        this.lifePoints = Utils.getRandomNumber(50,70);
        this.price = (int)(damage*3+lifePoints/damage*2-damage)-25;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void getCardData() {
        System.out.println(getId() + " | " + getName() +" | \uD83D\uDCB0 1" + getPrice() + " | ⚔ " + getDamage() + " | \uD83C\uDF94 " + getLifePoints());
    }
}
