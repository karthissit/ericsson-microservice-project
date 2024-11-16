package com.ericsson.api_gw_co.controller;


import com.ericsson.api_gw_co.dto.CustomerShoppingCartRequest;
import com.ericsson.api_gw_co.service.ShoppingCartService;
import com.ericsson.api_gw_co.service.value_object.ShoppingCartProductsWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ApiGatewayCoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ApiGatewayCOController apiGatewayController;


    @Test
    void testCustomerShoppingCart() throws Exception {

        CustomerShoppingCartRequest request = new CustomerShoppingCartRequest();
        request.setCustomerId(123L);
        ShoppingCartProductsWrapper wrapper = new ShoppingCartProductsWrapper();
        when(shoppingCartService.getShoppingCartItems(123L)).thenReturn(wrapper);

        mockMvc.perform(post("/customershoppingcart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":123}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
