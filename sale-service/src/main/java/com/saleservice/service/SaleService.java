package com.saleservice.service;

import com.saleservice.model.Sale;
import com.saleservice.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepository;

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Sale> getProductsBySale(Long id_sale) {
        return null;
    }

    @Override
    public Sale createSale(Sale sale) {
        return null;
    }
}
