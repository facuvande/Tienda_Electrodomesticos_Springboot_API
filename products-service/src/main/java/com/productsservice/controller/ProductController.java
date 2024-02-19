package com.productsservice.controller;

import com.productsservice.model.Product;
import com.productsservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id_product}")
    public ResponseEntity<?> getProductById(@PathVariable Long id_product){
        Product myProduct = productService.getProductById(id_product);
        if(myProduct != null){
            return ResponseEntity.ok(myProduct);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @GetMapping("/details")
    List<Product> getProductsById(@RequestParam List<Long> id_products){
        return productService.getProductsById(id_products);
    }


    @PostMapping("")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id_product}")
    public String deleteProduct(@PathVariable Long id_product){
        return productService.deleteProductById(id_product);
    }

    @PutMapping("/{id_product}")
    public Product editProduct(@PathVariable Long id_product, @RequestBody Product newProduct){
        return productService.editProduct(id_product, newProduct);
    }
}
