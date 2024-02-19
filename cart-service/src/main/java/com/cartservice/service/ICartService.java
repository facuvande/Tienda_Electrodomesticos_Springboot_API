package com.cartservice.service;

import com.cartservice.model.Cart;

import java.util.List;

public interface ICartService {

    public List<Cart> getCarts();
    public Cart getCartById(Long id);
    public Cart createCart(Cart cart);
    public String deleteCartById(Long id);
    public Cart addProductToCart(Long id_product, Long id_cart);
    public Cart removeProductFromCart(Long id_product, Long id_cart);


}
