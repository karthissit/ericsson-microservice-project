package com.ericsson.shoppingcart_service.controller;

import com.ericsson.shoppingcart_service.dto.ShoppingCartResponse;
import com.ericsson.shoppingcart_service.dto.ShoppingCartXmlWrapper;
import com.ericsson.shoppingcart_service.entity.ShoppingCart;
import com.ericsson.shoppingcart_service.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    private List<ShoppingCart> shoppingCarts;
    private ShoppingCartXmlWrapper shoppingCartXmlWrapper;

    @BeforeEach
    void setUp() {
        shoppingCarts = Arrays.asList(
                new ShoppingCart(1L, 10L, 5, 123L),
                new ShoppingCart(2L, 11L, 2, 123L),
                new ShoppingCart(3L, 12L, 4, 123L)
        );



        shoppingCartXmlWrapper = new ShoppingCartXmlWrapper(shoppingCarts.stream().map(
                shoppingCart ->
                        ShoppingCartResponse.builder()
                                .id(shoppingCart.getId())
                                .quantity(shoppingCart.getQuantity())
                                .customerId(shoppingCart.getCustomerId())
                                .productId(shoppingCart.getProductId())
                                .build()
        ).collect(Collectors.toList()));

        when(shoppingCartService.getShoppingCarts(anyLong())).thenReturn(shoppingCarts);
        when(shoppingCartService.getShoppingCartsXmlResponse(anyLong())).thenReturn(shoppingCartXmlWrapper);
    }

    @Test
    void testGetShoppingCarts() throws Exception {
        String jsonRequest = "123";
        String jsonResponse = "[{\"id\":1,\"productId\":10,\"quantity\":5,\"customerId\":123},{\"id\":2,\"productId\":11,\"quantity\":2,\"customerId\":123},{\"id\":3,\"productId\":12,\"quantity\":4,\"customerId\":123}]";

        mockMvc.perform(post("/getshoppingcarts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void testGetShoppingCartForXmlRequest() throws Exception {
        String xmlRequest = "<ShoppingCartXmlRequest><customerId>123</customerId></ShoppingCartXmlRequest>";
        String xmlResponse = "<ShoppingCartXmlWrapper><shoppingCarts><shoppingCart><id>1</id><productId>10</productId><quantity>5</quantity><customerId>123</customerId></shoppingCart><shoppingCart><id>2</id><productId>11</productId><quantity>2</quantity><customerId>123</customerId></shoppingCart><shoppingCart><id>3</id><productId>12</productId><quantity>4</quantity><customerId>123</customerId></shoppingCart></shoppingCarts></ShoppingCartXmlWrapper>";

        mockMvc.perform(post("/getshoppingcarts")
                        .contentType(MediaType.APPLICATION_XML)
                        .content(xmlRequest)
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().xml(xmlResponse));
    }
}

