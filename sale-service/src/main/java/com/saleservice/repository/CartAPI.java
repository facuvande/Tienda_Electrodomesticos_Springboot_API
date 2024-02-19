package com.saleservice.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cart-service", url = "http://localhost:8082/carts")
public interface CartAPI {


}
