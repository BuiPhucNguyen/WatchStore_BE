package bpn.MockProject.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import bpn.MockProject.entity.Order;

@Data
@Builder
public class OrderModel {
    private Integer id;
    private Integer accountId;
    private String address;
    private String phone;
    private Date date;
    private List<OrderDetailModel> detailModels;
    private BigDecimal orderDetailsPrice;
    private BigDecimal shipPrice;
    private BigDecimal totalPrice;
    private String note;
    private String status;

    public static OrderModel transform(Order order){
        return OrderModel.builder()
        		.id(order.getId())
                .accountId(order.getAccount().getId())
                .address(order.getAddress())
                .phone(order.getPhone())
                .date(order.getCreatedDate())
                .detailModels(order.getOrderDetails().stream().map(OrderDetailModel::transform).collect(Collectors.toList()))
                .orderDetailsPrice(order.getOrderDetailsPrice())
                .shipPrice(order.getShipPrice())
                .totalPrice(order.getTotalPrice())
                .note(order.getNote())
                .status(order.getStatus())
                .build();
    }
}
