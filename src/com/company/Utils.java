package com.company;

public class Utils {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static void createDelay(int sec) {
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printer(String line, int delay) {
        String[] array =  line.split("");

        for (String s : array) {
            try {
                Thread.sleep(delay);
                System.out.print(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculateDamage() {
        return getRandomNumber(31,50);
    }
}
