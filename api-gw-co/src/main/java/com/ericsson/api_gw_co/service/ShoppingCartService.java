package com.ericsson.api_gw_co.service;


import com.ericsson.api_gw_co.service.value_object.Product;
import com.ericsson.api_gw_co.service.value_object.ShoppingCart;
import com.ericsson.api_gw_co.service.value_object.ShoppingCartProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static com.ericsson.api_gw_co.constants.AppConstants.PRODUCT_SERVICE_URI;
import static com.ericsson.api_gw_co.constants.AppConstants.SHOPPING_CART_SERVICE_URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {

    @Autowired
    private final RestTemplate restTemplate;

    public List<ShoppingCartProducts> getShoppingCartItems(Long customerId) {
        //call product serivce
        //call shopping cart service

        List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<>();

        ShoppingCart[] shoppingCarts = restTemplate.getForObject(
                SHOPPING_CART_SERVICE_URI + "/getshoppingcarts?customerId=" + customerId,
                ShoppingCart[].class);
        if(shoppingCarts != null){
            log.info("Found shopping carts for the customer {}", customerId);
            List<Long> productIds = Arrays.stream(shoppingCarts).map(
                    ShoppingCart::getProductId
            ).collect(Collectors.toList());
            String endPoint = getProductsEndPoint(productIds);
            Product[] products = restTemplate.getForObject(
                    endPoint, Product[].class
            );
            log.info("Found products for the cutomerid {}",Arrays.toString(products));

            shoppingCartProducts = combineArrays(shoppingCarts, products);

        }else{
            log.error("Shopping carts not found for customer {}", customerId);
        }

        return shoppingCartProducts;
    }

    private String getProductsEndPoint(List<Long> productIds) {
        StringBuilder builder = new StringBuilder(PRODUCT_SERVICE_URI);
        builder.append("/products?")
                .append("ids=");
        for (int i = 0; i < productIds.size(); i++) {
            builder.append(productIds.get(i));
            if(i+1 < productIds.size()){
                builder.append(",");
            }
        }

        return builder.toString();
    }

    public static List<ShoppingCartProducts> combineArrays(ShoppingCart[] shoppingCarts, Product[] products) {
        Map<Long, Product> productMap = new HashMap<>();
        for (Product product : products) {
            productMap.put(product.getId(), product);
        }

        List<ShoppingCartProducts> combinedList = new ArrayList<>();
        for (ShoppingCart cart : shoppingCarts) {
            Product product = productMap.get(cart.getProductId());
            if (product != null) {
                ShoppingCartProducts scp = new ShoppingCartProducts(
                        cart.getId(),
                        product.getId(),
                        cart.getQuantity(),
                        cart.getCustomerId(),
                        product.getName(),
                        product.getPrice()
                );
                combinedList.add(scp);
            }
        }

        return combinedList;
    }
}
