package com.saleservice.service;

import com.saleservice.model.Sale;

import java.util.List;

public interface ISaleService {
    public List<Sale> getSales();
    public Sale getSaleById(Long id);
    public List<Sale> getProductsBySale(Long id_sale);
    public Sale createSale(Sale sale);

}
