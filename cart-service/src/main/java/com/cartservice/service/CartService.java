package com.cartservice.service;

import com.cartservice.model.Cart;
import com.cartservice.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository cartRepository;
    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public String deleteCartById(Long id) {
        cartRepository.deleteById(id);
        return "Cart deleted";
    }

    @Override
    public Cart editCart(Long id, Cart newCart) {
        Cart myCart = this.getCartById(id);
        if(myCart != null) {
            myCart.setTotal_price(newCart.getTotal_price());
            myCart.setId_products(newCart.getId_products());
            return cartRepository.save(myCart);
        }
        return null;
    }
}
