package com.test.henry;

import java.util.*;

public class OfferDaoImpl implements OfferDao{
    static Map<String, Offer> offers = new HashMap<>();

    @Override
    public Optional<Offer> getOne(String offerName) {
        return Optional.ofNullable(offers.get(offerName));
    }

    @Override
    public Set<Offer> getAll() {
        return new HashSet<>(offers.values());
    }

    @Override
    public void clear() {
        offers.clear();

    }

    @Override
    public void save(Offer offer) {
        offers.put(offer.getName(), offer);
    }

    @Override
    public void removeOffer(String offerName) {
        offers.remove(offerName);
    }
}
