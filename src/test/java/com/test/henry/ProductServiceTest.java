package com.test.henry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    ProductDao productDao;

    @InjectMocks
    ProductServiceImpl underTest;

    @Test
    void testGetProductByName(){
        Product product = new Product(1l, "soup", "tin", 0.65,null);
        when(productDao.getOne("soup")).thenReturn(Optional.of(product));
        Product one = underTest.getProductByName("soup");
        assertNotNull(one);
        assertEquals(product.getName(), one.getName());
    }

    @Test
    void testAllProducts(){
        Map<String, Product> products = new HashMap<>();
        Product product = new Product(1l, "soup", "tin", 0.65,null);
        products.put(product.getName(),product);
        when(productDao.getAll()).thenReturn(new HashSet<>(products.values()));
        List<Product> productList = underTest.getAllProducts();
        assertEquals(1, productList.size());
    }

}
