package com.ericsson.api_gw_co.controller;

import com.ericsson.api_gw_co.dto.CustomerShoppingCartRequest;
import com.ericsson.api_gw_co.service.ProductUpdateService;
import com.ericsson.api_gw_co.service.ShoppingCartService;
import com.ericsson.api_gw_co.service.value_object.ShoppingCartProductsWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ejb.EJB;

@RestController
@Slf4j
public class ApiGatewayCOController {

    @EJB
    private ProductUpdateService productUpdateService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(
            value = "/customershoppingcart",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    @Cacheable(
            value = "customerShoppingCartCache",
            key = "#customerId"
    )
    public ResponseEntity<Object> customerShoppingCart(@RequestBody CustomerShoppingCartRequest customerShoppingCartRequest) {
        // Get shopping cart items
        ShoppingCartProductsWrapper wrapper =
                shoppingCartService.getShoppingCartItems(customerShoppingCartRequest.getCustomerId());
        log.info("Found products {}", wrapper);
        return ResponseEntity.ok(wrapper);
    }

//    @CacheEvict(value = "customerShoppingCartCache", key = "#customerId")
//    public void evictCustomerShoppingCartCache(Long customerId) {
//        // TODO: Evict cache for a specific customer shopping cart
//    }

}
