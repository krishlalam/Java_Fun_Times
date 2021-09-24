package com.test.henry;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {
    private ProductDao productDao;
    private ShoppingCart underTest;

    @Before
    public void setup() {
        productDao = new ProductDaoImpl();
        insertProductData();
        underTest = new ShoppingCart(productDao);
    }

    @After
    public void tearDown() {
        productDao.clear();
        underTest=null;
    }

    @Test
    public void testScenario1(){
        List<String> shoppingCartItems = new ArrayList();
        shoppingCartItems.add("soup");
        shoppingCartItems.add("soup");
        shoppingCartItems.add("soup");
        shoppingCartItems.add("bread");
        shoppingCartItems.add("bread");
        assertEquals("3.15",underTest.calculateTotal(shoppingCartItems, LocalDate.now()));
    }

    @Test
    public void testScenario2(){
        List<String> shoppingCartItems = new ArrayList();
        shoppingCartItems.add("milk");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        assertEquals("1.90",underTest.calculateTotal(shoppingCartItems, LocalDate.now()));
    }

    @Test
    public void testScenario3(){
        List<String> shoppingCartItems = new ArrayList();
        shoppingCartItems.add("milk");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        assertEquals("1.84",underTest.calculateTotal(shoppingCartItems, LocalDate.now().plusDays(5)));
    }

    @Test
    public void testScenario4(){
        List<String> shoppingCartItems = new ArrayList();
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("apples");
        shoppingCartItems.add("soup");
        shoppingCartItems.add("soup");
        shoppingCartItems.add("bread");
        assertEquals("1.97",underTest.calculateTotal(shoppingCartItems, LocalDate.now().plusDays(5)));
    }



    private void insertProductData(){
        productDao.save(new Product(1l, "soup", "tin", 0.65,ProductOffer.BUY_2_SOUP_HALF_LOAF));
        productDao.save(new Product(2l, "bread", "loaf", 0.80,ProductOffer.NONE));
        productDao.save(new Product(3l, "milk", "bottle", 1.30,ProductOffer.NONE));
        productDao.save(new Product(4l, "apples", "single", 0.10,ProductOffer.TEN_PERCENT));
    }



}
