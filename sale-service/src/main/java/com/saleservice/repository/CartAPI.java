package com.saleservice.repository;

import com.saleservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service", url = "http://localhost:8082/carts")
public interface CartAPI {

    @GetMapping("/products/{id_cart}")
    public List<ProductDTO> getProductsByCart(@PathVariable Long id_cart);

    @GetMapping("/{id_cart}/totalAmmount")
    public Double getAmmountBySale(@PathVariable Long id_cart);

}
