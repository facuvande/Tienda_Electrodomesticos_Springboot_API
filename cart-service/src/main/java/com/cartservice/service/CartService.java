package com.cartservice.service;

import com.cartservice.dto.ProductDTO;
import com.cartservice.model.Cart;
import com.cartservice.repository.ICartRepository;
import com.cartservice.repository.ProductAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackGetCartProducts")
    @Retry(name="products-service")
    public List<ProductDTO> getProductsByCart(Long id_cart){
        Cart myCart = this.getCartById(id_cart);
        List<Long> productsId = myCart.getId_products();

        return productApi.getProductsById(productsId);
    }

    @Override
    public Double getTotalByCart(Long id_cart){
        Cart myCart = this.getCartById(id_cart);
        return myCart.getTotal_price();
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
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackAddProductsToCart")
    @Retry(name="products-service")
    public Cart addProductToCart(Long id_product, Long id_cart) {

        Cart myCart = this.getCartById(id_cart);
        Double totalPriceCart = myCart.getTotal_price();
        ProductDTO myProduct = productApi.getProductById(id_product);

        List<Long> myList = myCart.getId_products();
        myList.add(myProduct.getId_product());
        totalPriceCart = totalPriceCart + myProduct.getUnit_price();
        myCart.setId_products(myList);
        myCart.setTotal_price(totalPriceCart);

        cartRepository.save(myCart);
        return myCart;
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackRemoveProductsToCart")
    @Retry(name="products-service")
    public Cart removeProductToCart(Long id_product, Long id_cart) {

        Cart myCart = this.getCartById(id_cart);
        List<Long> myListProducts = myCart.getId_products();
        for(int i = myListProducts.size() - 1; i >= 0; i--){
            Long productId = myListProducts.get(i);

            if(productId.equals(id_product)){
                ProductDTO myProduct = productApi.getProductById(id_product);

                myCart.setTotal_price(myCart.getTotal_price() - myProduct.getUnit_price());
                myListProducts.remove(i);
            }
        }
        myCart.setId_products(myListProducts);

        return cartRepository.save(myCart);
    }

    public List<ProductDTO> fallbackGetCartProducts(Throwable t){
        List<ProductDTO> myList = new ArrayList<>();
        ProductDTO myProductError = new ProductDTO(1111111L, t.getMessage() , "Error", 00000.0);
        myList.add(myProductError);
        return myList;
    }

    public Cart fallbackAddProductsToCart(Throwable t){
        return null;
    }

    public Cart fallbackRemoveProductsToCart(Throwable t){
        return null;
    }

}
