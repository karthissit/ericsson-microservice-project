package com.ericsson.product_service.controller;


import com.ericsson.product_service.entity.Product;
import com.ericsson.product_service.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductsService productsService;

    @PostMapping
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products){
        try{
            List<Product> saved = productsService.saveProducts(products);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam List<Long> ids) {
        log.info("Getting products {}", ids);
        return productsService.getProducts(ids);
    }

    @PostMapping("/updateprice")
    public void updatePrice() {
        log.info("Updating prices");
        productsService.updatePrices();
    }
}
