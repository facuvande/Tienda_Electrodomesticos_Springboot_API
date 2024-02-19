package com.cartservice.service;

import com.cartservice.dto.ProductDTO;
import com.cartservice.model.Cart;

import java.util.List;

public interface ICartService {

    public List<Cart> getCarts();
    public Cart getCartById(Long id);
    public List<ProductDTO> getProductsByCart(Long id_cart);
    public Cart createCart(Cart cart);
    public String deleteCartById(Long id);
    public Cart addProductToCart(Long id_product, Long id_cart);
    public Cart removeProductToCart(Long id_product, Long id_cart);


}
