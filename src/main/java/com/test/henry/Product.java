package com.test.henry;

public class Product {
    private  Long id;
    private String name;
    private String unit;
    private double price;
    private Offer offer;

    public Product(Long id, String name, String unit, double price,Offer offer) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.offer = offer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public Offer getOffer(){
        return offer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
