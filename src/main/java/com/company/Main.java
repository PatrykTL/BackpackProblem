package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    static double maxWeight = 20;
    static double maxPrize = 20;
    static int numberOfItems = 10;
    static int numberOfGenerations = 10;
    static int population = 30;
    static int mutationPercentChance = 10;
    static double backPackLimit = 40;

    public static void main(String[] args) {
        ArrayList<Item> items = genItems(numberOfItems);
        GenericAlgorithm genAlg = new GenericAlgorithm(items,numberOfGenerations,population,mutationPercentChance,backPackLimit);
        printItems(items);
    }

    private static ArrayList<Item> genItems(int itemsNumber){
        ArrayList<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < itemsNumber; i++){
            itemsList.add(createItem(i,maxWeight,maxPrize));
        }
        return itemsList;
    }


    private static Item createItem(int itemNumber, double maxPrize, double maxWeight) {
        Random generator = new Random();
        double p = generator.nextDouble() * maxPrize * 100;
        double w = generator.nextDouble() * maxWeight * 100;
        return new Item(itemNumber,Math.round(p) / 100 , Math.round(w) / 100);
    }

    private static void printItems(ArrayList<Item> items){
        items.forEach(item -> {
            item.toString();
        });
    }
}
