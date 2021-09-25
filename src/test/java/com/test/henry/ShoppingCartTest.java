package com.test.henry;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {
    private ShoppingCart underTest;
    private ProductService productService;
    private ProductDao productDao;
    private OfferDao offerDao;

    @Before
    public void setup() {
        offerDao = new OfferDaoImpl();
        productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
        StubProductAndOfferData stubProductAndOfferData = new StubProductAndOfferData(productDao,offerDao);
        stubProductAndOfferData.insertProductAndOfferData();
        underTest = new ShoppingCart(productService);
    }

    @After
    public void tearDown() {
        productDao.clear();
        offerDao.clear();
        productService= null;
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

}
