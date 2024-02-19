package com.productsservice.service;

import com.productsservice.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getProducts();
    public Product getProductById(Long id);
    public List<Product> getProductsById(List<Long> id_products);
    public Product createProduct(Product product);
    public String deleteProductById(Long id);
    public Product editProduct(Long id, Product newProduct);

}
