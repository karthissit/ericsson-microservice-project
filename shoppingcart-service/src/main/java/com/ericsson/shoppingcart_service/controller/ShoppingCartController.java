package com.ericsson.shoppingcart_service.controller;



import com.ericsson.shoppingcart_service.entity.ShoppingCart;
import com.ericsson.shoppingcart_service.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/getshoppingcarts")
public class ShoppingCartController {

    @Autowired
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCart> getShoppingCarts(@RequestParam Long customerId) {
        return shoppingCartService.getShoppingCarts(customerId);
    }

}
