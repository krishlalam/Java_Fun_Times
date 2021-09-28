package com.test.henry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ShoppingCart {

    private final ProductService productService;

    public ShoppingCart(ProductService productService) {
        this.productService = productService;
    }

    public  Map<String, Long> checkoutProductAndQtys(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getName, LinkedHashMap<String, Long>::new, Collectors.counting()));

    }

    private List<ProductEntry> generateProductEntry(Map<String, Long> productAndQtys, LocalDate localDate ) {
        return productAndQtys.entrySet().stream()
                .map(entry -> {
                    Product product = productService.getProductByName(entry.getKey());
                    if (product.getOffer() == null || product.getOffer().getName().equals("NONE")) {
                        return new ProductEntry(product, entry.getValue(), product.getPrice(), 0);
                    }
                    if (product.getOffer().getName().equals("BUY_2_SOUP_HALF_LOAF")) {
                        return new ProductEntry(product, entry.getValue(), product.getPrice(),
                                product.getOffer().getProductOfferLamda().applyDiscount(product.getOffer().getValidFrom(),product.getOffer().getValidTo(), localDate,
                                        entry.getValue(), // qty
                                        productService.getProductByName(product.getOffer().getDiscountedItemName()).getPrice()));
                    }
                    else {
                        return new ProductEntry(product,
                                entry.getValue(), // qty
                                product.getPrice(),
                                product.getOffer().getProductOfferLamda().applyDiscount(product.getOffer().getValidFrom(),product.getOffer().getValidTo(), localDate,
                                        entry.getValue(), product.getPrice()));
                    }
                }).collect(Collectors.toList());
    }

    public String calculateTotal(List<String> items, LocalDate localDate){
        List<Product> shoppingItems = new ArrayList<>();
        List<Double> discounts = new ArrayList<>();
        items.forEach(item -> shoppingItems.add(productService.getProductByName(item)));
        Map<String, Long> checkoutItems = checkoutProductAndQtys(shoppingItems);
        List<ProductEntry> productEntries = generateProductEntry(checkoutItems,localDate);
        AtomicReference<Double> total = new AtomicReference<>(productEntries.stream().mapToDouble(entry -> {
            discounts.add(entry.discountedPrice);
            return (entry.price * entry.qty);
        }).sum());
        discounts.forEach(discount -> total.updateAndGet(v -> new Double((double) (v - discount))));
        return String.format("%.2f", total.get());
    }

}
