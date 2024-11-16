package com.ericsson.shoppingcart_service.service;


import com.ericsson.shoppingcart_service.dto.ShoppingCartResponse;
import com.ericsson.shoppingcart_service.dto.ShoppingCartXmlWrapper;
import com.ericsson.shoppingcart_service.entity.ShoppingCart;
import com.ericsson.shoppingcart_service.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {

    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getShoppingCarts(Long customerId) {
        return shoppingCartRepository.findByCustomerId(customerId);
    }

    public ShoppingCartXmlWrapper getShoppingCartsXmlResponse(long customerId) {
        List<ShoppingCartResponse> result = shoppingCartRepository.findByCustomerId(customerId).stream()
                .map(
                        shoppingCart -> ShoppingCartResponse.builder()
                                .id(shoppingCart.getId())
                                .productId(shoppingCart.getProductId())
                                .quantity(shoppingCart.getQuantity())
                                .customerId(shoppingCart.getCustomerId())
                                .build()
                ).collect(Collectors.toList());
        return ShoppingCartXmlWrapper.builder()
                .shoppingCartResponses(result)
                .build();
    }
}
