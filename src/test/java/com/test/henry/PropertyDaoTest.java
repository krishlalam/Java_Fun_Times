package com.test.henry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyDaoTest {

    private ProductDao underTest;

    @BeforeEach
    void setup(){
        underTest = new ProductDaoImpl();
    }
    @AfterEach
    void tearDown(){
        underTest.clear();
    }
    @Test
    void shouldSaveProduct(){
        Offer offer =new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0);
        Product product = new Product(1l, "soup", "tin", 0.65,offer);
        underTest.save(product);
        Optional<Product> one = underTest.getOne(product.getName());
        assertTrue(one.isPresent());
        assertEquals(1L, one.get().getId());
    }

    @Test
    void shouldReturnOptionalEmptyWhenProductNotFound() {
        Optional<Product> one = underTest.getOne("test");
        assertTrue(!one.isPresent());
    }

    @Test
    void shouldReturnAllProdcuts() {
        Offer offer =new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0);
        Product product = new Product(1l, "soup", "tin", 0.65,offer);
        underTest.save(product);
        Set<Product> productSet = underTest.getAll();
        assertEquals(1, productSet.size());
    }

    @Test
    void shouldRemoveOffer(){
        Offer offer =new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0);
        Product product = new Product(1l, "soup", "tin", 0.65,offer);
        underTest.save(product);
        Optional<Product> one = underTest.getOne(product.getName());
        assertNotNull(one.get().getOffer().getName());
        assertEquals("NONE",one.get().getOffer().getName());
        underTest.removeProductOffer("soup");
        one = underTest.getOne(product.getName());
        assertNull(one.get().getOffer());

    }
    @Test
    void shouldAddOfferToProduct(){
        Offer offer =new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0);
        Product product = new Product(1l, "soup", "tin", 0.65,null);
        underTest.save(product);
        Optional<Product> one = underTest.getOne(product.getName());
        assertNull(one.get().getOffer());
        underTest.addOrUpdateProductOffer("soup",offer);
        one = underTest.getOne(product.getName());
        assertNotNull(one.get().getOffer().getName());
        assertEquals("NONE",one.get().getOffer().getName());
    }

    @Test
    void shouldRemoveProduct(){
        Product product = new Product(1l, "soup", "tin", 0.65,null);
        underTest.save(product);
        Optional<Product> one = underTest.getOne(product.getName());
        assertNotNull(one.get());
        underTest.removeProduct("soup");
        assertTrue(!underTest.getOne(product.getName()).isPresent());
    }

}
