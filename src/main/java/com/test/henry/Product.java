package com.test.henry;

public class Product {
    private  Long id;
    private String name;
    private String unit;
    private double price;
    private ProductOffer productOffer;

    public Product(Long id, String name, String unit, double price,ProductOffer productOffer) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.productOffer = productOffer;
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

    public ProductOffer getProductOffer(){
        return productOffer;
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

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }
}
