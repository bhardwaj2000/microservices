package com.example.curd.service;

import com.example.curd.entity.Product;

import java.util.List;

public interface CurdService {
    void save(Product product);

    List<Product> getAllProduct();

    Product getProductById(Long productId) throws NoSuchFieldException;

    void updateProduct(Long productId, Product product);
}
