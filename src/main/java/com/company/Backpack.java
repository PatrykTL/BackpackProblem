package com.company;

import java.util.ArrayList;
import lombok.Getter;

@Getter
public class Backpack {
    private final ArrayList<Item> itemsArrayList;
    private final double maxWeight;
    private double value;
    private double weight;

    public Backpack(double maxWeight){
        itemsArrayList = new ArrayList<>();
        this.maxWeight = maxWeight;
    }

    public boolean add(Item item) {
        if(!itemsArrayList.add(item)){
            return false;
        }
        this.value += item.getPrice();
        this.weight += item.getWeight();
        return true;
    }
}
