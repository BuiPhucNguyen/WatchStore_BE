package bpn.MockProject.enums;

public enum OrderStatusEnum {
	PENDING("Pending"),
	COMPLETE("Complete"),
	CANCEL("Cancel");
	
	private String status;

	private OrderStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
