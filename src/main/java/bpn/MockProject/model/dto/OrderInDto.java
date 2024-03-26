package bpn.MockProject.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;


@Data
public class OrderInDto {
	private String address;
	private String phone;
	private List<OrderDetailInDto> orderDetails;
	private BigDecimal shipPrice;
	private String note;
}
