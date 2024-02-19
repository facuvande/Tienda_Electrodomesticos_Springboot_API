package com.cartservice.controller;

import com.cartservice.dto.ProductDTO;
import com.cartservice.model.Cart;
import com.cartservice.service.CartService;
import com.cartservice.service.ICartService;
import feign.FeignException;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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

    @GetMapping("/products/{id_cart}")
    public List<ProductDTO> getProductsByCart(@PathVariable Long id_cart){
        return cartService.getProductsByCart(id_cart);
    }

    @GetMapping("/{id_cart}/totalAmmount")
    public Double getTotalByCart(@PathVariable Long id_cart){
        return cartService.getTotalByCart(id_cart);
    }

    @PostMapping("")
    public Cart createCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    @DeleteMapping("/{id_cart}")
    public String deleteCart(@PathVariable Long id_cart){
        return cartService.deleteCartById(id_cart);
    }

    @PostMapping("/{id_cart}/product/{id_product}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long id_product, @PathVariable Long id_cart){
        try{
            return ResponseEntity.ok(cartService.addProductToCart(id_product, id_cart));
        }catch (FeignException e){
            String errorMessage = "Product by id: " + id_product + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/{id_cart}/product/{id_product}")
    public ResponseEntity<?> removeProductToCart(@PathVariable Long id_product, @PathVariable Long id_cart){
        try{
            return ResponseEntity.ok(cartService.removeProductToCart(id_product, id_cart));
        }catch (FeignException e){
            String errorMessage = "Product by id: " + id_product + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

}
