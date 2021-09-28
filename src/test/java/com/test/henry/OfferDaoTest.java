package com.test.henry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OfferDaoTest {

    private OfferDao underTest;

    @BeforeEach
    void setup(){
        underTest = new OfferDaoImpl();
    }
    @AfterEach
    void tearDown(){
        underTest.clear();
    }
    @Test
    void shouldSaveOffer(){
        Offer offer =new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0);
        underTest.save(offer);
        Optional<Offer> one = underTest.getOne(offer.getName());
        assertTrue(one.isPresent());
        assertEquals("NONE", one.get().getName());
    }

    @Test
    void shouldReturnOptionalEmptyWhenOfferNotFound() {
        Optional<Offer> one = underTest.getOne("NONE");
        assertTrue(!one.isPresent());
    }

    @Test
    void shouldReturnAllOffers() {
        Offer offer =new Offer("NONE", null, null, null, (LocalDate validFrom, LocalDate validTo, LocalDate saleDate, long itemQty, double itemPrice) -> 0);
        underTest.save(offer);
        Set<Offer> offerSet = underTest.getAll();
        assertEquals(1, offerSet.size());
    }

}
