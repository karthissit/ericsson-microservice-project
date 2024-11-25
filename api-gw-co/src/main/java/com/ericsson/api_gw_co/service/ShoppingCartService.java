package com.ericsson.api_gw_co.service;


import com.ericsson.api_gw_co.service.value_object.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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

    public ShoppingCartProductsWrapper getShoppingCartItems(Long customerId) {
        //call product serivce
        //call shopping cart service

        List<ShoppingCartProducts> shoppingCartProducts;
        ShoppingCartProductsWrapper wrapper = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Long> requestEntity = new HttpEntity<>(customerId, headers);
        RestTemplate restTemplate = new RestTemplate();
        ShoppingCart[] shoppingCarts = restTemplate.postForObject(
                SHOPPING_CART_SERVICE_URI + "/getshoppingcarts",
                requestEntity,
                ShoppingCart[].class);

        if (shoppingCarts != null) {
            log.info("Found shopping carts for the customer {}", customerId);
            List<Long> productIds = Arrays.stream(shoppingCarts).map(
                    ShoppingCart::getProductId
            ).collect(Collectors.toList());

            IdsRequest idsRequest = new IdsRequest(productIds);

            HttpEntity<IdsRequest> productRequestEntity = new HttpEntity<>(idsRequest, headers);

            String endPoint = getProductsEndPoint(productIds);

            Product[] products = restTemplate.postForObject(
                    endPoint, productRequestEntity, Product[].class
            );
            log.info("Found products for the cutomerid {}", Arrays.toString(products));

            shoppingCartProducts = combineArrays(shoppingCarts, Objects.requireNonNull(products));
            wrapper = ShoppingCartProductsWrapper.builder()
                    .shoppingCarts(shoppingCartProducts)
                    .total(shoppingCartProducts.stream()
                            .mapToLong(
                                    ShoppingCartProducts::getPrice
                            ).sum())
                    .build();
        } else {
            log.error("Shopping carts not found for customer {}", customerId);
        }

        return wrapper;
    }

    private String getProductsEndPoint(List<Long> productIds) {
        return PRODUCT_SERVICE_URI + "/products?";
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
