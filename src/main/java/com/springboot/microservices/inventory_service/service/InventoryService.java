package com.springboot.microservices.inventory_service.service;

import com.springboot.microservices.inventory_service.model.Product;
import com.springboot.microservices.inventory_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductQuantity(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(new Product());
        int newQuantity = product.getQuantity() + quantity;
        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    public int getProductAvailability(Long productId) {
        Product product = productRepository.findById(productId).orElse(new Product());
        return product.getQuantity();
    }

    public String orderProduct(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(new Product());
        String result;
        double price = 0;

        try {
            if (product.getQuantity() >= quantity) {
                price = quantity * product.getPrice();

                product.setQuantity(product.getQuantity() - quantity);
                productRepository.save(product);

                result = "Product ordered successfully. Total price will be Rp "+price;
            } else {
                result = "There are only "+product.getQuantity()+" left.";
            }
            return result;
        } catch (Exception e) {
            e.getLocalizedMessage();
            return "Unable to find Product";
        }
    }
}