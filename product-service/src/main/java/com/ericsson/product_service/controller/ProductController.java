package com.ericsson.product_service.controller;


import com.ericsson.product_service.entity.Product;
import com.ericsson.product_service.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductsService productsService;

    @GetMapping
    public List<Product> getProducts(@RequestParam List<Long> ids) {
        return productsService.getProducts(ids);
    }

    @PostMapping("/updateprice")
    public void updatePrice() {
        log.info("Updating prices");
        productsService.updatePrices();
    }
}
