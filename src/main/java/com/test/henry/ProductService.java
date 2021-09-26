package com.test.henry;

import java.util.List;

public interface ProductService {

    Product getProductByName(String name);
    List<Product> getAllProducts();
    void removeOffer(String productName);
    void updateProductOffer(String productName, Offer offer);
    void removeProduct(String productName);
    void addProduct(Product product);
}
