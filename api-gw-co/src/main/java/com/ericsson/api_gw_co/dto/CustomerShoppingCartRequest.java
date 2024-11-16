package com.ericsson.api_gw_co.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerShoppingCartRequest {
    private Long customerId;
}
