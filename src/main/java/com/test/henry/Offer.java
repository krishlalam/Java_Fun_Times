package com.test.henry;

import java.time.LocalDate;

public class Offer {
    private final String name;
    private final String discountedItemName;
    private final ProductOfferLamda productOfferLamda;
    private final LocalDate validFrom;
    private final LocalDate validTo;

    public Offer(String name, String discountedItemName, LocalDate validFrom, LocalDate validTo, ProductOfferLamda productOfferLamda) {
        this.name = name;
        this.discountedItemName = discountedItemName;
        this.productOfferLamda = productOfferLamda;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public String getName() {
        return name;
    }

    public String getDiscountedItemName() {
        return discountedItemName;
    }

    public ProductOfferLamda getProductOfferLamda() {
        return productOfferLamda;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }
}
