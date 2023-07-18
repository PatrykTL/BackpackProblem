package com.company;

import lombok.Getter;

@Getter
public class Item {
    private final String name;
    private final double weight;
    private final double price;

    public Item(int numberItem, double price, double weight) {
        this.name = "Item: " + numberItem;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString(){
        return "Name: " + name + "\nPrice: " + price + "\nWeight: " + weight;
    }
}
