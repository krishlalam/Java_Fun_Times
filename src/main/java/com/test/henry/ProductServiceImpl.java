package com.test.henry;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getProductByName(String productName) {
        return productDao.getOne(productName).orElseThrow(() -> new ProductNotFoundException(productName));
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDao.getAll());
    }

    @Override
    public void removeOffer(String productName) {
        Product product = this.getProductByName(productName);
        product.setOffer(null);
        productDao.save(product);
    }

    @Override
    public void updateProductOffer(String productName, Offer offer) {
        Product product = this.getProductByName(productName);
        product.setOffer(offer);
        productDao.save(product);

    }

    @Override
    public void removeProduct(String productName) {
        productDao.removeProduct(productName);
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }
}
