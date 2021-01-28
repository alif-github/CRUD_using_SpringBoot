package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Cart;

import java.util.List;

public interface CartRepository {

    List<Cart> findAllCart();

    List<Cart> findAllCartSave();

    Cart findById(String idCart);

    Cart findByCustomerId(int idCustomer);

    void saveCart(Cart cart);

    int updateCart(Cart cart);

    String deleteCartById(String idCart);

    void deleteAllCart();
}
