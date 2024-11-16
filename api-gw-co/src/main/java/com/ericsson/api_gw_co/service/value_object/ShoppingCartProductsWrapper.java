package com.ericsson.api_gw_co.service.value_object;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ShoppingCartProductsWrapper {
    private Long total;
    private List<ShoppingCartProducts> shoppingCarts;
}
