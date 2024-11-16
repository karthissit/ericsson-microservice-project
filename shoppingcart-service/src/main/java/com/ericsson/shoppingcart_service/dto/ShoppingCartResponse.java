package com.ericsson.shoppingcart_service.dto;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@XmlRootElement(name = "row")
public class ShoppingCartResponse {
    private Long id;
    private Long productId;
    private int quantity;
    private Long customerId;
}
