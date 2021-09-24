package com.test.henry;

public class StubProductData {

    private final ProductDao productDao;

    public StubProductData(ProductDao productDao){
        this.productDao = productDao;
    }

    public void insertProductData(){
        productDao.save(new Product(1l, "soup", "tin", 0.65,ProductOffer.BUY_2_SOUP_HALF_LOAF));
        productDao.save(new Product(2l, "bread", "loaf", 0.80,ProductOffer.NONE));
        productDao.save(new Product(3l, "milk", "bottle", 1.30,ProductOffer.NONE));
        productDao.save(new Product(4l, "apples", "single", 0.10,ProductOffer.TEN_PERCENT));
    }

}
