package com.example.brunobacarini.myapp;

/**
 * Created by brunobacarini on 23/02/2017.
 */

public class Car {
    private String model;
    private String brand;
    private String owner;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Car(String brand, String model, String owner){
        this.model = model;
        this.brand = brand;
        this.owner = owner;
    }
}
