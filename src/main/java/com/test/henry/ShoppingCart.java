package com.test.henry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {

    private final ProductDao productDao;

    public ShoppingCart(ProductDao productDao) {
        this.productDao = productDao;
    }

    public  Map<String, Long> checkoutQtys(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getName, LinkedHashMap<String, Long>::new, Collectors.counting()));

    }

    private List<ProductEntry> generateProductEntry(Map<String, Long> qtys,Map<String, Product> inventory , LocalDate localDate ) {
        return qtys.entrySet().stream()
                .map(entry -> {
                    Product product = productDao.getProducts().get(entry.getKey());
                    if (product.getProductOffer() == ProductOffer.NONE) {
                        return new ProductEntry(product, entry.getValue(), product.getPrice(), 0);
                    }
                    if (product.getProductOffer() == ProductOffer.BUY_2_SOUP_HALF_LOAF) {
                        return new ProductEntry(product, entry.getValue(), product.getPrice(),
                                product.getProductOffer().productOfferLamda.applyDiscount(product.getProductOffer().validFrom,product.getProductOffer().validTo, localDate,
                                        entry.getValue(),
                                        inventory.get(product.getProductOffer().discountedItemName).getPrice()));
                    } else {
                        return new ProductEntry(product,
                                entry.getValue(),
                                product.getPrice(),
                                product.getProductOffer().productOfferLamda.applyDiscount(product.getProductOffer().validFrom,product.getProductOffer().validTo, localDate,
                                        entry.getValue(), product.getPrice()));
                    }
                }).collect(Collectors.toList());
    }

    public String calculateTotal(List<String> items, LocalDate localDate){
        List<Product> shoppingItems = new ArrayList<>();
        items.stream().forEach(item -> shoppingItems.add(productDao.getProducts().get(item)));
        Map<String, Long> checkouItems = checkoutQtys(shoppingItems);
        List<ProductEntry> productEntries = generateProductEntry(checkouItems,productDao.getProducts(),localDate);
        double total = productEntries.stream().mapToDouble(entry ->
        {
            return (entry.price * entry.qty) - entry.discountedPrice;
        }).sum();

        return String.format("%.2f",total);
    }

}
