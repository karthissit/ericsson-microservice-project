package com.ericsson.shoppingcart_service.repository;

import com.ericsson.shoppingcart_service.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByCustomerId(Long customerId);
}
