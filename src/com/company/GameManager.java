package com.company;

import java.util.List;
import java.util.Scanner;

public class GameManager {

    private Player player;
    private Player computer;

    private boolean playerLost = false;
    private boolean computerLost = false;

    private static Scanner scanner = new Scanner(System.in);

    public void start() {
        Heroes.addHeroes();
        menu();
    }

    private void menu() {
        System.out.println("Select an option:\n1. Play Game\n2. How to play");
        int selectedOption = scanner.nextInt();
        if(selectedOption == 1) {
            playGame();
        } else if(selectedOption == 2){
            howToPlay();
        } else{
            System.out.println("Invalid Choices");
        }
    }


    private void playGame() {

        player = new Player("Player");
        computer = new Player("Computer");

        cardShop();

        while (player.getHealth()>0 && computer.getHealth()>0){
            playCards();
            playerLost = player.cardRefill();
            computerLost = computer.cardRefill();
            boolean over = gameOver();
            if(over){
                return;
            }
        }

//        player.currentState(true);
//        computer.currentState(true);
    }

    private boolean gameOver() {
        if(playerLost||(player.getHealth()<=0&&player.getHealth()<computer.getHealth())){
            System.out.println("Game Over, You Lost");
            return true;
        }
        else if(computerLost||(computer.getHealth()<=0&&computer.getHealth()<player.getHealth())){
            System.out.println("Congrats! You Won");
            return true;
        }
        return false;
    }

    private void playCards() {
        System.out.println("Choose a card to attack");
        player.currentState(true);

            int attackId = scanner.nextInt();
            Card attackCard = player.searchFromPlayerList(attackId);
            if(attackCard == null) {
                System.out.println("INVALID CARD ID TRY AGAIN");
            }
            else{
                Card computerAttackCard = selectComputerCard();
                attack(attackCard ,computerAttackCard);
            }

    }

    private Card selectComputerCard() {
        int randomId = Utils.getRandomNumber(1,Heroes.getHeroes().size()+1);
        Card attackCard = computer.searchFromPlayerList(randomId);
        if(attackCard == null){
            return selectComputerCard();
        }
        return attackCard;
    }

    private void howToPlay() {
        System.out.println("Choose 'Play game' in the menu section\n");
        System.out.println("After choosing this option you will get a list of cards available in game, so that you can choose from those\n");
        System.out.println("Now choose some of the cards you want, from the list\n");
        System.out.println("note: you can only choose card by the coins you have, so be aware\n");
        System.out.println("When you complete choosing card, write 0 to finish choosing cards.\n");
        System.out.println("Now you will get a list of cards you chose, select a card from this list for the attack.\n");
        System.out.println("Now continue the process of selecting the cards from your card list, until the game goes on.");
        System.out.println("Press 0 to go back to the Menu.");
        int zero = scanner.nextInt();
        if(zero == 0){
            menu();
        }
        else{
            return;
        }
    }

    public void attack(Card playerCard, Card computerCard) {
        System.out.println("\n\n\n$------------Attack Started-----------$");
        System.out.println("\t\t" + playerCard.getName() + "  ===VS===  " + computerCard.getName());

        Utils.printer("\n\t\t\t⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔⚔\n\n", 200);

        Utils.createDelay(1);


        int playerDamageTaken = (int)((computerCard.getDamage()*.8+computerCard.getLifePoints()*.6)/5);
        int computerDamageTaken = (int)((playerCard.getDamage()*.8+playerCard.getLifePoints()*.6)/5);
        int playerCoinsGained = (int)(computerDamageTaken*1.5);
        int computerCoinsGained = (int)(playerDamageTaken*1.5);

        player.updateStats(playerCoinsGained, playerDamageTaken, playerCard);
        computer.updateStats(computerCoinsGained, computerDamageTaken, computerCard);
//        player.currentState(true);
//        computer.currentState(true);1

        System.out.println("\t\t\t  health: -" + playerDamageTaken);
        System.out.println("\t\t\t  coins: +" + playerCoinsGained);
        System.out.println("\t\t   damage given: " + computerDamageTaken);
        System.out.println("\n");

        player.resourceStats();
        Utils.createDelay(1);
        computer.resourceStats();


        System.out.println("\n$-------------Attack Over-------------$\n\n\n");

        Utils.createDelay(1);

    }




    private void cardShop() {
        List<Card> heroes = Heroes.getHeroes();
        for (Card hero : heroes) {
            hero.getCardData();
        }

        while(computer.getCoins() > 0){
            int randomCardId = Utils.getRandomNumber(1, heroes.size()+1);
            boolean success = computer.addCard(randomCardId);
            if(!success){
                break;
            }
        }
        computer.currentState(false);

        boolean doneChoosing = false;
        while(player.getCoins() > 0 && doneChoosing == false){
            System.out.println("Choose card with id (0 to finish) : ");
            int cardId = scanner.nextInt();
            if(cardId == 0){
                doneChoosing = true;
                continue;
            }
            player.addCard(cardId);
            player.currentState(false);
        }
    }

}
