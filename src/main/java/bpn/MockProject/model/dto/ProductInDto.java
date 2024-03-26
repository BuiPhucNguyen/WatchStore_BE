package bpn.MockProject.model.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProductInDto {
	private Integer brandId;
    private String name;
    private String description;
    private BigDecimal price;
}
