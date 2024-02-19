package com.cartservice.controller;

import com.cartservice.model.Cart;
import com.cartservice.service.CartService;
import com.cartservice.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ICartService cartService;


    @GetMapping("")
    public List<Cart> getCarts(){
        return cartService.getCarts();
    }

    @GetMapping("/{id_cart}")
    public ResponseEntity<?> getCartById(@PathVariable Long id_cart){
        Cart myCart = cartService.getCartById(id_cart);
        if(myCart != null){
            return ResponseEntity.ok(myCart);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        }
    }

    @PostMapping("")
    public Cart createCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    @DeleteMapping("/{id_cart}")
    public String deleteCart(@PathVariable Long id_cart){
        return cartService.deleteCartById(id_cart);
    }

}
