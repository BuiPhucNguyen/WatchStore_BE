package bpn.MockProject.model.entity;

import java.math.BigDecimal;

import bpn.MockProject.entity.OrderDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailModel {
	private Integer id;
	private Integer productId;
	private Integer amount;
	private BigDecimal purchasePrice;
	
	public static OrderDetailModel transform(OrderDetail entity) {
		return OrderDetailModel.builder()
				.id(entity.getId())
				.productId(entity.getProduct().getId())
				.amount(entity.getAmount())
				.purchasePrice(entity.getPurchasePrice())
				.build();
	}
}
