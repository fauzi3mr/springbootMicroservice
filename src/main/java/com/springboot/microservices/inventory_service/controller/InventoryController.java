package com.springboot.microservices.inventory_service.controller;

import com.springboot.microservices.inventory_service.model.Product;
import com.springboot.microservices.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getListProduct() {
        List<Product> products = inventoryService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = inventoryService.addNewProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/products/update/{productId}")
    public ResponseEntity<Product> updateProductQuantity(
            @PathVariable Long productId, @RequestParam int quantity) {
        Product updatedProduct = inventoryService.updateProductQuantity(productId, quantity);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/products/availability/{productId}")
    public ResponseEntity<Integer> getProductAvailability(@PathVariable Long productId) {
        int availability = inventoryService.getProductAvailability(productId);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @PostMapping("/products/order/{productId}")
    public ResponseEntity<String> orderProduct(@PathVariable Long productId, @RequestParam int quantity) {
        String result = inventoryService.orderProduct(productId, quantity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}