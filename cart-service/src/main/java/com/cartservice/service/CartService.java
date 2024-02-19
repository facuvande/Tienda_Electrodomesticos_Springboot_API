package com.cartservice.service;

import com.cartservice.dto.ProductDTO;
import com.cartservice.model.Cart;
import com.cartservice.repository.ICartRepository;
import com.cartservice.repository.ProductAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ProductAPI productApi;

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
    public Cart addProductToCart(Long id_product, Long id_cart) {

        // Traemos carrito
        Cart myCart = this.getCartById(id_cart);

        // Traemos Producto
        ProductDTO myProduct = productApi.getProductById(id_product);

        // Traemos Lista de Productos del carrito actual
        List<Long> myList = myCart.getId_products();
        myList.add(myProduct.getId_product());

        // Agregamos producto a carrito
        myCart.setId_products(myList);

        cartRepository.save(myCart);
        return myCart;
    }

    @Override
    public Cart removeProductFromCart(Long id_product, Long id_cart) {
        return null;
    }
}
