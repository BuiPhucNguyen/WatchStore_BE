package bpn.MockProject.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import bpn.MockProject.entity.Product;

@Data
@Builder
public class ProductModel {
    private Integer id;
    private BrandModel brand;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer sold;
    private Integer stock;
    private Boolean status;

    public static ProductModel transform(Product product){
        return ProductModel.builder().id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sold(product.getSold())
                .stock(product.getStock())
                .status(product.getStatus())
                .brand(BrandModel.transform(product.getBrand()))
                .build();
    }
}
