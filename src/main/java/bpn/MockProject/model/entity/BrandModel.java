package bpn.MockProject.model.entity;

import bpn.MockProject.entity.Brand;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandModel {
    private Integer id;
    private String name;
    private boolean status;
    public static BrandModel transform(Brand brand){
        return BrandModel.builder()
                .id(brand.getId())
                .name(brand.getName())
                .status(brand.getStatus())
                .build();
    }
}
