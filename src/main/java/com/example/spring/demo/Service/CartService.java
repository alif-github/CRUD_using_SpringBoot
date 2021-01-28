package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAllCart();

    List<Cart> findAllCartSave();

    Cart findById(String idCart);

    Cart findByCustomerId(int idCustomer);

    void saveCart(Cart cart);

    int updateCart(Cart cart);

    int deleteCartById(int idCart);

    void deleteAllCart();

    boolean isCartExist(Cart cart);
}
