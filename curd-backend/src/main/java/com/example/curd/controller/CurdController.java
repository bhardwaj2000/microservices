package com.example.curd.controller;


import com.example.curd.entity.Product;
import com.example.curd.service.CurdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class CurdController {

    private final CurdService curdService;

    public CurdController(CurdService curdService) {
        this.curdService = curdService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        curdService.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) throws NoSuchFieldException {
        Product productById = curdService.getProductById(productId);
        return ResponseEntity.ok(productById);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productList =  curdService.getAllProduct();
        return ResponseEntity.ok(productList);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestParam("productId") Long productId, @RequestBody Product product){
        curdService.updateProduct(productId, product);
        return ResponseEntity.ok(product);
    }
}
