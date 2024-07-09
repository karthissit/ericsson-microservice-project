package com.ericsson.api_gw_co.service.value_object;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCart {
    private Long id;
    private Long productId;
    private int quantity;
    private Long customerId;
}