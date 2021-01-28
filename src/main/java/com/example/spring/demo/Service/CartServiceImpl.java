package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Cart;
import com.example.spring.demo.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Cart> findAllCart() {
        List<Cart> carts = cartRepository.findAllCart();
        return carts;
    }

    @Override
    public List<Cart> findAllCartSave() {
        List<Cart> carts = cartRepository.findAllCartSave();
        return null;
    }

    @Override
    public Cart findById(String idCart) {
        Cart obj;
        try {
            obj = cartRepository.findById(idCart);
        } catch (Exception e) {
            System.out.println(e);
            obj = null;
        }
        return obj;
    }

    @Override
    public Cart findByCustomerId(int idCustomer) {
        Cart obj;
        try {
            obj = cartRepository.findByCustomerId(idCustomer);
        } catch (Exception e) {
            System.out.println(e);
            obj = null;
        }
        return obj;
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.saveCart(cart);
    }

    @Override
    public int updateCart(Cart cart) {
        return 0;
    }

    @Override
    public int deleteCartById(int idCart) {
        return 0;
    }

    @Override
    public void deleteAllCart() {

    }

    @Override
    public boolean isCartExist(Cart cart) {
        return cartRepository.findById(cart.getIdCart()) != null;
    }
}
