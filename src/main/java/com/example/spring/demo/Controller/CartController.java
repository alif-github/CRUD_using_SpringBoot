package com.example.spring.demo.Controller;

import com.example.spring.demo.Model.Cart;
import com.example.spring.demo.Service.CartService;
import com.example.spring.demo.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartService cartService;

    // -------------------Retrieve All data Cart--------------------------------------------

    @RequestMapping(value = "/cart/", method = RequestMethod.GET)
    public ResponseEntity<List<Cart>> listAllCart() {
        List<Cart> carts = cartService.findAllCart();
        if (carts.isEmpty()) {
            return new ResponseEntity<>(carts, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // -------------------Retrieve Single data Cart------------------------------------------

    @RequestMapping(value = "/cart/{idCart}", method = RequestMethod.GET)
    public ResponseEntity<?> getCart(@PathVariable("idCart") String idCart) {
        logger.info("Fetching Cart with idCart {}", idCart);
        Cart cart = cartService.findById(idCart);
        if (cart == null) {
            logger.error("Cart with idCart {} not found.", idCart);
            return new ResponseEntity<>(new CustomErrorType("Cart with idCart " + idCart + " not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // -------------------Create a data Cart-------------------------------------------

    @RequestMapping(value = "/cart/", method = RequestMethod.POST)
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        logger.info("Creating Cart : {}", cart);
        cartService.saveCart(cart);
        List<Cart> carts = cartService.findAllCartSave();
        return new ResponseEntity<>(carts, HttpStatus.CREATED);
    }
}
