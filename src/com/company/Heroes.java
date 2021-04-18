package com.company;

import java.util.ArrayList;
import java.util.List;

class Heroes{
    private static List<Card> heroes = new ArrayList();

    public static void addHeroes() {
        heroes.add(new Card(1,"Alpha"));
        heroes.add(new Card(2,"Beta"));
        heroes.add(new Card(3,"Charlie"));
        heroes.add(new Card(4,"Delta"));
        heroes.add(new Card(5,"Echo"));
        heroes.add(new Card(6,"Foxtrot"));
        heroes.add(new Card(7,"Golf"));
        heroes.add(new Card(8,"Hotel"));
        heroes.add(new Card(9,"India"));
        heroes.add(new Card(10,"Juliett"));
    }

    public static List getHeroes() {
        return heroes;
    }
}