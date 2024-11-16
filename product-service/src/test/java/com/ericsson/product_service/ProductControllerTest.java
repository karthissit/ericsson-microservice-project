package com.ericsson.product_service;


import com.ericsson.product_service.controller.ProductController;
import com.ericsson.product_service.entity.Product;
import com.ericsson.product_service.service.ProductsService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductController productController;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = Arrays.asList(
                new Product(1L, "Product 1", 100),
                new Product(2L, "Product 2", 200)
        );

        when(productsService.getProducts(Arrays.asList(1L, 2L))).thenReturn(products);
        when(productsService.saveProducts(any())).thenReturn(products);
    }

    @Test
    void testAddProducts() throws Exception {
        String jsonRequest = "[{\"id\":1,\"name\":\"Product 1\",\"price\":100},{\"id\":2,\"name\":\"Product 2\",\"price\":200}]";

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRequest));
    }

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .param("ids", "1,2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'Product 1','price':100},{'id':2,'name':'Product 2','price':200}]"));
    }

    @Test
    void testUpdatePrice() throws Exception {
        mockMvc.perform(post("/products/updateprice")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productsService).updatePrices();
    }
}
