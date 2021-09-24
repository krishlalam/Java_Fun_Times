package com.test.henry;

import java.time.LocalDate;

@FunctionalInterface
public interface ProductOfferLamda {
    public double applyDiscount(LocalDate validFrom,LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice);
}
