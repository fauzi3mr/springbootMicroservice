package com.springboot.microservices.inventory_service.service;

import com.springboot.microservices.inventory_service.model.Product;
import com.springboot.microservices.inventory_service.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
    public void findAllProduct(){
        //precondition
        List<Product> products = inventoryService.getAllProduct();
        given(productRepository.findAll()).willReturn(products);

        //action
        List<Product> allProduct = inventoryService.getAllProduct();

        //verify the output
        assertThat(allProduct).isNotNull();
    }

    @Test
    @Order(2)
    public void addNewProduct(){
        //precondition
        given(productRepository.save(product)).willReturn(product);

        //action
        Product savedProduct = inventoryService.addNewProduct(product);

        //verify the output
        assertThat(savedProduct).isNotNull();
    }

    @Test
    @Order(3)
    public void updateProductQuantity(){
        //precondition
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        product.setQuantity(product.getQuantity()+5);
        given(productRepository.save(product)).willReturn(product);

        //action
        Product updatedProduct = inventoryService.updateProductQuantity(1L, 5);

        //verify the output
        assertThat(updatedProduct).isNotNull();
    }

    @Test
    @Order(4)
    public void getProductAvailability(){
        //precondition
        given(product.getQuantity()).willReturn(10);

        //action
        int quantity = inventoryService.getProductAvailability(1L);

        //verify the output
        assertThat(quantity).isEqualTo(10);
    }


}