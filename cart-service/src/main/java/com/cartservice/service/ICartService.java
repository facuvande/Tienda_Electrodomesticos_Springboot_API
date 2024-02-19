package com.cartservice.service;

import com.cartservice.model.Cart;

import java.util.List;

public interface ICartService {

    public List<Cart> getCarts();
    public Cart getCartById(Long id);
    public Cart createCart(Cart cart);
    public String deleteCartById(Long id);

}
