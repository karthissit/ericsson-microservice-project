package com.ericsson.product_service.service;


import com.ericsson.product_service.entity.Product;
import com.ericsson.product_service.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsService {

    @Autowired
    private final ProductsRepository productRepository;

    public List<Product> getProducts(List<Long> ids) {
        log.info("getting all products");
        return productRepository.findByIdIn(ids);
    }

    public void updatePrices() {
        Random random = new Random();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            log.info("updating prices for the product {}",product.getId());
            product.setPrice(random.nextInt(1000) + 1);
            productRepository.save(product);
        }
    }

}

