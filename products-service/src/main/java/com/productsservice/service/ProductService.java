package com.productsservice.service;

import com.productsservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public String deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product editProduct(Long id, Product newProduct) {
        return null;
    }
}
