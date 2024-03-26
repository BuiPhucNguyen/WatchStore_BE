package bpn.MockProject.model.dto;

import lombok.Data;

import java.util.List;

import bpn.MockProject.model.entity.OrderModel;

@Data
public class OrderOutDto {
    private List<OrderModel> orders;
    private Integer total;
}
