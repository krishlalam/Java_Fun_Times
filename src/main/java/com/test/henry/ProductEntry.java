package com.test.henry;

public class ProductEntry {
    public final Product product;
    public final long qty;
    public final double price;
    public final double discountedPrice;

    public ProductEntry(Product product, long qty, double price, double discountedPrice) {
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public ProductEntry(Product product, long qty, double price) {
       this(product,qty,price,0);
    }
}
