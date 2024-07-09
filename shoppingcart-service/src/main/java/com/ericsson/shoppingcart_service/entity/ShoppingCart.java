package com.ericsson.shoppingcart_service.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="t_shopping_cart")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ProductId")
    private Long productId;
    @Column(name="Quantity")
    private int quantity;
    @Column(name="CustomerId")
    private Long customerId;
}