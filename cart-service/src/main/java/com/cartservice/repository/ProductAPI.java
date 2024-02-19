package com.cartservice.repository;

import com.cartservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="products-service", url = "http://localhost:8081/products")
public interface ProductAPI {

    @GetMapping("/{id_product}")
    public ProductDTO getProductById(@PathVariable ("id_product") Long id_product);

}
