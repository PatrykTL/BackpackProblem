package com.company;

import java.util.ArrayList;
import java.util.Random;

public class GenericAlgorithm {
    private double backPackLimit;
    private ArrayList<Chromosome> entities;
    private ArrayList<Item> items;
    private int numberOfGenerations;
    private int mutationPercentChance;
    private double fitnessSum;
    private Chromosome chromosome;
    private ArrayList<Chromosome> newEntities;
    private Chromosome crossBreedOne;
    private Chromosome crossBreedTwo;


    public GenericAlgorithm(ArrayList list, int numberOfGenerations, int population,int mutationPercentChance, double backPackLimit){
        this.backPackLimit = backPackLimit;
        entities = new ArrayList<>();
        newEntities = new ArrayList<>();
        this.mutationPercentChance = mutationPercentChance;
        this.items = list;
        this.numberOfGenerations = numberOfGenerations;
        generateStartPopulation(population);
        algorithmLoop(this.numberOfGenerations);
        printPopulation();
    }

    private void generateStartPopulation(int numberOfEntity){
        for(int i=0; i<numberOfEntity; i++){
            entities.add(new Chromosome(randomChromosomeTable(items.size()), items, backPackLimit));
        }
    }

    private int[] randomChromosomeTable(int listLength){
        int[] tab = new int[listLength];
        Random random = new Random();
        for (int i = 0; i < listLength; i++){
            tab[i] = random.nextInt(2);
        }
        return tab;
    }
    private void algorithmLoop(int numberOfGenerations){
        for(int i = 0 ; i < numberOfGenerations ; i ++){
            selection();
            crossBreedAll();
            mutation(mutationPercentChance);
            System.out.println("Generation " + (i+1));
            printPopulation();
        }
    }
    private void selection(){
        int numberOfSelected = entities.size();
        for(int i = 0; i < numberOfSelected; i++) {
            fitnessSum = 0;
            entities.forEach(entity -> {
                fitnessSum += entity.fitness();
            });
            Random random = new Random();
            double selectedValue = random.nextDouble() * fitnessSum;

            fitnessSum = 0;
            entities.forEach(entity -> {
                fitnessSum += entity.fitness();
                if (fitnessSum > selectedValue) {
                    chromosome = entity;
                    return;
                }
            });

            newEntities.add(chromosome);
            entities.remove(chromosome);
        }

    }

    private void crossBreedAll(){
        int newEntitiesSize = newEntities.size();
        for(int i = 0; i < newEntitiesSize; i = i + 2) {
            Random random = new Random();
            int chromosomeIndex1 = random.nextInt(newEntities.size());
            Chromosome chromosome1 = newEntities.get(chromosomeIndex1);
            newEntities.remove(chromosomeIndex1);
            if(newEntities.isEmpty()){
                entities.add(chromosome1);
                return;
            }
            int chromosomeIndex2 = random.nextInt(newEntities.size());
            Chromosome chromosome2 = newEntities.get(chromosomeIndex2);
            newEntities.remove(chromosomeIndex2);
            crossBreeding(chromosome1, chromosome2);
            entities.add(crossBreedOne);
            entities.add(crossBreedTwo);
        }

    }

    private void crossBreeding(Chromosome chromosome1, Chromosome chromosome2){
        Random random = new Random();
        int crossBrededGen = random.nextInt(chromosome1.getGenTable().length - 1);
        int[] newGenTable = chromosome1.getGenTable();
        int[] newGenTable2 = chromosome2.getGenTable();
        for(int i = crossBrededGen; i < chromosome1.getGenTable().length; i ++){
            newGenTable[i] = chromosome2.getGenTable()[i];
            newGenTable2[i] = chromosome1.getGenTable()[i];
        }
        crossBreedOne = new Chromosome(newGenTable,items,backPackLimit);
        crossBreedTwo = new Chromosome(newGenTable2,items,backPackLimit);
    }

    private void mutation(int mutationPercentChance){
        entities.forEach(entity ->{
            Random random = new Random();
            if(random.nextInt(100) < mutationPercentChance){
                entity.mutation();
            }
        });
    }
    private void printPopulation(){
        entities.forEach(entity -> {
            System.out.println(entity.toString());
        });
    }
}
