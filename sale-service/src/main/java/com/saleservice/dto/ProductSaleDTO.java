package com.saleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO {
    private Long id_sale;
    private LocalDate date_sale;
    private Long id_cart;
    private List<ProductDTO> listProducts;
}
