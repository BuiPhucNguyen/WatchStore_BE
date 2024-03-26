package bpn.MockProject.model.dto;

import lombok.Data;

import java.util.List;

import bpn.MockProject.model.entity.BrandModel;

@Data
public class BrandOutDto {
    private List<BrandModel> brands;
    private Integer total;
}
