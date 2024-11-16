package com.ericsson.api_gw_co.ejb;

import com.ericsson.api_gw_co.service.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.ejb.Singleton;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class ProductPriceUpdateEJB {

    @Autowired
    private final ProductUpdateService productService;

    @Scheduled(cron = "0 * * * * *")
    public void updateProductPrices() {
        log.info("Updating product prices...");
        productService.updatePrices();
    }
}

