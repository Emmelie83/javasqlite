package com.emmeliejohansson.data.entities;

public class AvailableCar {
    private String regNr;
    private String brand;
    private String color;
    private String categoryName;
    private int pricePerDay;

    public AvailableCar(String regNr, String brand, String color, String categoryName, int pricePerDay) {
        this.regNr = regNr;
        this.brand = brand;
        this.color = color;
        this.categoryName = categoryName;
        this.pricePerDay = pricePerDay;
    }

    public String getRegNr() {
        return regNr;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }
}
