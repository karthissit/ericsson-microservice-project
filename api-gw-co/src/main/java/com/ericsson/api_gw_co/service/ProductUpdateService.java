package com.ericsson.api_gw_co.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.ericsson.api_gw_co.constants.AppConstants.PRODUCT_SERVICE_URI;

@Service
@RequiredArgsConstructor
public class ProductUpdateService {

    @Autowired
    private final RestTemplate restTemplate;

    public void updatePrices() {
        restTemplate.patchForObject(PRODUCT_SERVICE_URI + "updateprice",null, Void.class);
    }
}
