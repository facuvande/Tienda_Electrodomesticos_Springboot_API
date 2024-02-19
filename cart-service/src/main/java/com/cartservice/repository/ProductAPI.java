package com.cartservice.repository;

import com.cartservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="products-service", url = "http://localhost:8081/products")
public interface ProductAPI {

    @GetMapping("/{id_product}")
    public ProductDTO getProductById(@PathVariable ("id_product") Long id_product);

    @GetMapping("/details")
    public List<ProductDTO> getProductsById(@RequestParam List<Long> id_products);

}
