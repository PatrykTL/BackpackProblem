package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class Chromosome {

    private final int[] genTable;
    private final ArrayList<Item> items;
    private double maxValue;
    private double maxWeight;
    private double backPackLimit;

    public Chromosome(int[] table, ArrayList<Item> items, double backPackLimit){
        this.backPackLimit = backPackLimit;
        this.genTable = table;
        this.items = items;
        maxValue = 0;
        maxWeight = 0;
        items.forEach(item -> {
            maxValue += item.getPrice();
            maxWeight += item.getWeight();
        });
    }

    //public Chromosome crossBreding(Chromosome secondChromosome){
    //    Random random = new Random();
    //    int crossBrededGen = random.nextInt(genTable.length);
    //    int[] newGenTable = this.genTable;
//
    //    for(int i = crossBrededGen - 1; i < genTable.length; i ++){
    //        newGenTable[i] = secondChromosome.getGenTable()[i];
    //    }
//
//
    //    return new Chromosome(newGenTable, this.items);
    //}
    public double fitness(){
        double value = 0;
        double weight = 0;
        for(int i = 0; i < genTable.length; i++){
            if(genTable[i]==1){
                value += items.get(i).getPrice();
                weight += items.get(i).getWeight();
            }
        }
        return (value / maxValue) + fitBackpackPoint(weight);
    }

    private double fitBackpackPoint(double weight){
        if(weight <= backPackLimit){
            return 1;
        }
        return 0;
    }

    public void mutation(){
        Random random = new Random();
        int mutatedGen = random.nextInt(genTable.length);
        mutateGen(mutatedGen);
    }

    private void mutateGen(int i){
        if(genTable[i] == 0){
            genTable[i] = 1;
        }
        if(genTable[i] == 1){
            genTable[i] = 0;
        }
    }
    @Override
    public String toString(){
        String string = new String();
        string += "[";
        for(int i = 0 ; i < this.genTable.length ; i++){
            string += genTable[i] + " ";
        }
        string += "] fitness: ";

        string += this.fitness();
        return string;
    }

}
