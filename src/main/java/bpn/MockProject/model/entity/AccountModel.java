package bpn.MockProject.model.entity;

import java.util.Date;

import bpn.MockProject.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountModel {
	private Integer id;
	private String userName;
	private Boolean status;
	private String firstName;
	private String lastName;
	private String email;
	private Date createdDate;
	
	public static AccountModel transform(Account enity) {
		return AccountModel.builder()
				.id(enity.getId())
				.userName(enity.getUserName())
				.status(enity.getStatus())
				.firstName(enity.getFirstName())
				.lastName(enity.getLastName())
				.email(enity.getEmail())
				.createdDate(enity.getCreatedDate())
				.build();	
	}

}
