package com.saleservice.controller;

import com.saleservice.dto.ProductDTO;
import com.saleservice.dto.ProductSaleDTO;
import com.saleservice.model.Sale;
import com.saleservice.service.ISaleService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping("")
    public List<Sale> getSales(){
        return saleService.getSales();
    }

    @GetMapping("/{id_sale}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id_sale){
        Sale mySale = saleService.getSaleById(id_sale);
        if(mySale != null){
            return ResponseEntity.ok(mySale);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");
        }
    }

    @GetMapping("/products/{id_sale}")
    public ProductSaleDTO getProductsBySale(@PathVariable Long id_sale){
        return saleService.getProductsBySale(id_sale);
    }

    @GetMapping("/{id_sale}/total_price")
    public Double getAmmountBySale(@PathVariable Long id_sale){
        return saleService.getAmmountBySale(id_sale);
    }

    @PostMapping("")
    public Sale createSale(@RequestBody Sale sale){
        return saleService.createSale(sale);
    }

}
