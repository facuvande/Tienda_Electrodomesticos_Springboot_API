package com.saleservice.service;

import com.saleservice.dto.ProductSaleDTO;
import com.saleservice.model.Sale;

import java.util.List;

public interface ISaleService {
    public List<Sale> getSales();
    public Sale getSaleById(Long id);
    public ProductSaleDTO getProductsBySale(Long id_sale);
    public Sale createSale(Sale sale);

}
