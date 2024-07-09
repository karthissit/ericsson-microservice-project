package com.ericsson.shoppingcart_service.service;


import com.ericsson.shoppingcart_service.entity.ShoppingCart;
import com.ericsson.shoppingcart_service.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {

    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getShoppingCarts(Long customerId) {
        return shoppingCartRepository.findByCustomerId(customerId);
    }
}
