package com.springboot.microservices.inventory_service.service;

import com.springboot.microservices.inventory_service.model.Product;
import com.springboot.microservices.inventory_service.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private Product product;

    @BeforeEach
    public void setup(){
        product = new Product(1L, "Product1", 1, 10);
    }

    @Test
    @Order(1)
    public void addProduct(){
        //precondition
        given(productRepository.save(product)).willReturn(product);

        //action
        Product savedProduct = inventoryService.addNewProduct(product);

        //verify the output
        assertThat(savedProduct).isNotNull();
    }
}
