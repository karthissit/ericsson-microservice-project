package com.ericsson.shoppingcart_service.controller;



import com.ericsson.shoppingcart_service.dto.ShoppingCartXmlRequest;
import com.ericsson.shoppingcart_service.dto.ShoppingCartXmlWrapper;
import com.ericsson.shoppingcart_service.entity.ShoppingCart;
import com.ericsson.shoppingcart_service.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/getshoppingcarts")
public class ShoppingCartController {

    @Autowired
    private final ShoppingCartService shoppingCartService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ShoppingCart> getShoppingCarts(@RequestBody Long customerId) {
        return shoppingCartService.getShoppingCarts(customerId);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ShoppingCartXmlWrapper getShoppingCartForXmlRequest(@RequestBody ShoppingCartXmlRequest shoppingCartXmlRequest) {
        return shoppingCartService.getShoppingCartsXmlResponse(shoppingCartXmlRequest.getCustomerId());
    }

}
