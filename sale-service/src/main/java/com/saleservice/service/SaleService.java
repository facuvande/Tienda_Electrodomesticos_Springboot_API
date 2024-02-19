package com.saleservice.service;

import com.saleservice.dto.ProductDTO;
import com.saleservice.dto.ProductSaleDTO;
import com.saleservice.model.Sale;
import com.saleservice.repository.CartAPI;
import com.saleservice.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private CartAPI cartApi;

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackGetProductsBySale")
    @Retry(name="cart-service")
    public ProductSaleDTO getProductsBySale(Long id_sale) {
        Sale mySale = this.getSaleById(id_sale);
        List<ProductDTO> myProducts = cartApi.getProductsByCart(mySale.getId_cart());

        ProductSaleDTO myDTO = new ProductSaleDTO();
        myDTO.setDate_sale(mySale.getDate_sale());
        myDTO.setId_sale(mySale.getId_sale());
        myDTO.setId_cart(mySale.getId_cart());
        myDTO.setListProducts(myProducts);

        return myDTO;
    }

    @Override
    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackGetAmmountBySale")
    @Retry(name="cart-service")
    public Double getAmmountBySale(Long id_sale) {
        Sale mySale = this.getSaleById(id_sale);
        return cartApi.getAmmountBySale(mySale.getId_cart());
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public ProductSaleDTO fallbackGetProductsBySale(){
        return null;
    }

    public Double fallbackGetAmmountBySale(){
        return null;
    }
}
