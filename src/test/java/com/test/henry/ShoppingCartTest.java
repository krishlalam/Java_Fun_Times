package com.test.henry;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private ProductService productService;
    private ProductDao productDao;
    private OfferDao offerDao;

    @BeforeEach
    public void setup() {
        offerDao = new OfferDaoImpl();
        productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
        StubProductAndOfferData stubProductAndOfferData = new StubProductAndOfferData(productDao,offerDao);
        stubProductAndOfferData.insertProductAndOfferData();
        shoppingCart = new ShoppingCart(productService);
    }

    @AfterEach
    public void tearDown() {
        productDao.clear();
        offerDao.clear();
        productService= null;
        shoppingCart =null;
    }

    @Test
    public void testScenario1(){
        List<String> shoppingCartItems = new ArrayList();
        shoppingCartItems.add("soup");
        shoppingCartItems.add("soup");
        shoppingCartItems.add("soup");
        shoppingCartItems.add("bread");
        shoppingCartItems.add("bread");
        assertEquals("3.15", shoppingCart.calculateTotal(shoppingCartItems, LocalDate.now()));
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
        assertEquals("1.90", shoppingCart.calculateTotal(shoppingCartItems, LocalDate.now()));
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
        assertEquals("1.84", shoppingCart.calculateTotal(shoppingCartItems, LocalDate.now().plusDays(5)));
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
        assertEquals("1.97", shoppingCart.calculateTotal(shoppingCartItems, LocalDate.now().plusDays(5)));
    }

}
