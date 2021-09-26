package com.test.henry;

import java.util.*;

public class ProductDaoImpl implements ProductDao{
    static Map<String, Product> products = new HashMap<>();

    @Override
    public Optional<Product> getOne(String productName) {
        return Optional.ofNullable(products.get(productName));
    }

    @Override
    public Set<Product> getAll() {
        return new HashSet<>(products.values());
    }

    @Override
    public void clear() {
        products.clear();

    }

    @Override
    public void save(Product product) {
        products.put(product.getName(), product);
    }

    @Override
    public void addOrUpdateProductOffer(String productName, Offer offer) {
        products.get(productName).setOffer(offer);
    }

    @Override
    public void removeProductOffer(String productName) {
        products.get(productName).setOffer(null);
    }

    @Override
    public void removeProduct(String productName) {
        products.remove(productName);
    }
}
