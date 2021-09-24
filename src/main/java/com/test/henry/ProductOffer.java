package com.test.henry;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public enum ProductOffer {
    NONE("", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0),
    TEN_PERCENT("TEN_PERCENT", null,  LocalDate.now().plusDays(3l),
            LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.lastDayOfMonth()),
            (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> {
                if (saleDate.isEqual(validFrom) || saleDate.isEqual(validFrom) || (saleDate.isAfter(validFrom) && saleDate.isBefore(validTo))) {
                    return (itemQty * itemPrice) * 0.1;
                }
                return 0;
            }),

    BUY_2_SOUP_HALF_LOAF("BUY_2_SOUP_HALF_LOAF", "bread",LocalDate.now().minusDays(1L), LocalDate.now().plusDays(7l)
            , (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double discountedItemPrice) -> {
        if (saleDate.isEqual(validFrom) || saleDate.isEqual(validFrom) || (saleDate.isAfter(validFrom) && saleDate.isBefore(validTo))) {
            if (itemQty >= 2) {
                return (discountedItemPrice * 0.5);
            }
        }
        return 0;
    });


    public final String productOfferName;
    public final String discountedItemName;
    public final ProductOfferLamda productOfferLamda;
    public final LocalDate validFrom;
    public final LocalDate validTo;

    ProductOffer(String productOfferName, String discountedItemName, LocalDate validFrom, LocalDate validTo, ProductOfferLamda productOfferLamda) {
        this.productOfferName = productOfferName;
        this.discountedItemName = discountedItemName;
        this.productOfferLamda = productOfferLamda;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }
}
