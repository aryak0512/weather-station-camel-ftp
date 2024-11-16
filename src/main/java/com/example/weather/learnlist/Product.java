package com.example.weather.learnlist;

import lombok.Data;

@Data
public class Product {

    private String name;
    private int id;
    private double price;

    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }
}
