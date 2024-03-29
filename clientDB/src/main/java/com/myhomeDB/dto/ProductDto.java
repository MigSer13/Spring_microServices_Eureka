package com.myhomeDB.dto;

import com.myhomeDB.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String categoryTitle;
    private BigDecimal price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        //this.categoryTitle = product.getCategory().getTitle();
        this.price = product.getPrice();
    }
}
