package com.ericsson.api_gw_co.service.value_object;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ShoppingCartProducts {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Long customerId;
    private String name;
    private Integer price;
}
