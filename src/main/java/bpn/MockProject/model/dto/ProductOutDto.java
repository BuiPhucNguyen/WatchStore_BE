package bpn.MockProject.model.dto;

import lombok.Data;

import java.util.List;

import bpn.MockProject.model.entity.ProductModel;

@Data
public class ProductOutDto {
    private List<ProductModel> products;
    private Integer total;
}
