package com.test.henry;

import java.util.Optional;
import java.util.Set;

public interface ProductDao {
    /**
     * Gets a product by its id.
     * @param productName The Id of the Product we wish to return.
     * @return an {@code Optional} {@link Product}. If no product can be found by the given Id will return empty.
     */
    Optional<Product> getOne(String productName);

    /**
     * @return A {@code Set} of all the products currently stored in this repository.
     */
    Set<Product> getAll();

    /**
     * Clears all the products currently stored in this repository.
     */
    void clear();

    /**
     * Saves the given {@link Product} to the repository. If a product entity with the same product already exists, it will be overridden.
     * @param product The {@code Product} to save to the repository.
     */
    void save(Product product);

    /**
     * Add or Update Offer to Product
     * @param productName The Id of the Product we wish to update and Offer.
     * @param offer the offer to add the product
     */
    void addOrUpdateProductOffer(String productName, Offer offer);


    /**
     * Removed Offer to Product
     * @param productName The Id of the Product we wish to update and Offer.
     */
    void removeProductOffer(String productName);


    /**
     * Remove  Product
     * @param productName The Id of the Product.
     */
    void removeProduct(String productName);

}
