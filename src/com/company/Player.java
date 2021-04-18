package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Player {

    private String name;
    private int coins;
    private double health;

    private ArrayList<Card> playerList;
    private ArrayList<Card> usedCardList;

    public ArrayList<Card> getPlayerList() {
        return playerList;
    }

    public ArrayList<Card> getUsedCardList() {
        return usedCardList;
    }

    public Card searchFromPlayerList(int id){
        for (Card card : playerList) {
            if(card.getId() == id){
                return card;
            }
        }
        return null;
    }

    public boolean cardRefill() {
        if(playerList.size() == 0){
            int length = usedCardList.size();
            for (int i=0; i<length; i++) {
                if(this.coins>= usedCardList.get(0).getPrice()){
                    playerList.add(usedCardList.get(0));
                    this.coins -= usedCardList.get(0).getPrice();
                    usedCardList.remove(usedCardList.get(0));
                }
            }
            if(playerList.size() == 0){
                return true;
            }
        }
        return false;
    }


    public Player(String name) {
        this.name = name;
        this.coins = 500;
        this.health = 100.0;
        this.playerList = new ArrayList<Card>();
        this.usedCardList = new ArrayList<Card>();
    }

    public void updateStats(int coins, double health, Card card){
        this.coins += coins;
        this.health -= health;
        playerList.remove(card);
        usedCardList.add(card);
    }


    public void resourceStats() {
        System.out.println("\t\t"+this.name.toUpperCase(Locale.ROOT) + " | " + "\uD83D\uDCB0" + this.coins + " | " + "\uD83C\uDF94" + this.health);
    }

    public boolean addCard(int cardId) {
        List<Card> tempCardList = Heroes.getHeroes();
        for (int i = 0; i < tempCardList.size(); i++) {
            if(tempCardList.get(i).getId()==cardId){
                if(coins >= tempCardList.get(i).getPrice()){
                    playerList.add(tempCardList.get(i));
                    coins -= tempCardList.get(i).getPrice();
                    return true;
                } else{
                    if(!this.name.toUpperCase(Locale.ROOT).equals("COMPUTER")){
                        System.out.println("You don't have enough coin to buy this card");
                    }
                    return false;
                }
            }
        }
        if(!this.name.toUpperCase(Locale.ROOT).equals("COMPUTER")){
            System.out.println("No Card available with this Id");
        }
        return false;
    }

    public void currentState(boolean fullStats) {
        System.out.println("Name: " + this.name.toUpperCase(Locale.ROOT));
        System.out.println("\uD83D\uDCB0 " + this.coins);
        if(fullStats){
            System.out.println("\uD83C\uDF94 " + this.health);
        }
        if(this.name.toUpperCase(Locale.ROOT).equals("COMPUTER")){
            return;
        }
        else{
            System.out.println("Your cards : ");
            for(int i=0; i<playerList.size(); i++){
                playerList.get(i).getCardData();
            }
        }

    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public double getHealth() {
        return health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}