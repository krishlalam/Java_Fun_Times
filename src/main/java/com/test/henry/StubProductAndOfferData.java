package com.test.henry;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class StubProductAndOfferData {

    private final ProductDao productDao;
    private final OfferDao offerDao;

    public StubProductAndOfferData(ProductDao productDao, OfferDao offerDao){
        this.productDao = productDao;
        this.offerDao = offerDao;
    }

    public void insertProductAndOfferData(){
        offerDao.save(new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0));
        offerDao.save(new Offer("TEN_PERCENT", null,  LocalDate.now().plusDays(3l),
                LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.lastDayOfMonth()),
                (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> {
                    if (saleDate.isEqual(validFrom) || saleDate.isEqual(validFrom) || (saleDate.isAfter(validFrom) && saleDate.isBefore(validTo))) {
                        return (itemQty * itemPrice) * 0.1;
                    }
                    return 0;
                }));
        offerDao.save(new Offer("BUY_2_SOUP_HALF_LOAF", "bread",LocalDate.now().minusDays(1L), LocalDate.now().plusDays(7l)
                , (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double discountedItemPrice) -> {
            if (saleDate.isEqual(validFrom) || saleDate.isEqual(validFrom) || (saleDate.isAfter(validFrom) && saleDate.isBefore(validTo))) {
                if (itemQty >= 2) {
                    return (discountedItemPrice * 0.5);
                }
            }
            return 0;
        }));
        productDao.save(new Product(1l, "soup", "tin", 0.65,offerDao.getOne("BUY_2_SOUP_HALF_LOAF").get()));
        productDao.save(new Product(2l, "bread", "loaf", 0.80,offerDao.getOne("NONE").get()));
        productDao.save(new Product(3l, "milk", "bottle", 1.30,offerDao.getOne("NONE").get()));
        productDao.save(new Product(4l, "apples", "single", 0.10,offerDao.getOne("TEN_PERCENT").get()));
    }


}
