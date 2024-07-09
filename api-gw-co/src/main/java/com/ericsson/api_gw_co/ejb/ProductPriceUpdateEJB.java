package com.ericsson.api_gw_co.ejb;

import com.ericsson.api_gw_co.service.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class ProductPriceUpdateEJB {

    @Autowired
    private final ProductUpdateService productService;

    @Schedule(minute = "*", hour = "*", persistent = false)
    public void updateProductPrices() {
        log.info("Updating product prices...");
        productService.updatePrices();
    }
}

