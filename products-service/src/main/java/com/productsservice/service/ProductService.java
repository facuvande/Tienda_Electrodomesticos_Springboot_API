package com.productsservice.service;

import com.productsservice.model.Product;
import com.productsservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsById(List<Long> id_products) {
        List<Product> myList = new ArrayList<>();
        for(Long id_product : id_products){
            System.out.println(id_product);
            Product product = this.getProductById(id_product);
            myList.add(product);
        }

        return myList;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public String deleteProductById(Long id) {
        productRepository.deleteById(id);
        return "Product Deleted";
    }

    @Override
    public Product editProduct(Long id, Product newProduct) {
        Product myProduct = this.getProductById(id);
        if(myProduct != null) {
            myProduct.setBrand(newProduct.getBrand());
            myProduct.setName(newProduct.getName());
            myProduct.setUnit_price(newProduct.getUnit_price());
            return productRepository.save(myProduct);
        }
        return null;
    }
}
