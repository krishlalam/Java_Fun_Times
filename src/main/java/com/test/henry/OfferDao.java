package com.test.henry;

import java.util.Optional;
import java.util.Set;

public interface OfferDao {

    /**
     * Gets a Offer by its name.
     * @param offerName The name of the Offer we wish to return.
     * @return an {@code Optional} {@link Offer}. If no product can be found by the given Id will return empty.
     */
    Optional<Offer> getOne(String offerName);


    /**
     * @return A {@code Set} of all the offers currently stored in this repository.
     */
    Set<Offer> getAll();

    /**
     * Clears all the offers currently stored in this repository.
     */
    void clear();

    /**
     * Saves the given {@link Offer} to the repository. If a product entity with the same product already exists, it will be overridden.
     * @param offer The {@code Offer} to save to the repository.
     */
    void save(Offer offer);
    /**
     * Remove  offer
     * @param offerName The Id of the Product.
     */
    void removeOffer(String offerName);
}
