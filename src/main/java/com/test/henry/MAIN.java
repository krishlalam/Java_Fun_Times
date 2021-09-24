package com.test.henry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MAIN {

    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoImpl();
        StubProductData stubProductData = new StubProductData(productDao);
        stubProductData.insertProductData();
        ShoppingCart shoppingCart = new ShoppingCart(productDao);
        System.out.println("Enter the product(bar codes to checkout and 'stop'  or press enter to checkout");
        List<String> items = new ArrayList<>();
        Scanner in = new Scanner(System.in);
       while(true){
           String item = in.nextLine();
           if(item.equals("stop") || item.isEmpty()){
               break;
           }
          items.add(item);
       }
       System.out.println(shoppingCart.calculateTotal(items, LocalDate.now()));
    }

}
