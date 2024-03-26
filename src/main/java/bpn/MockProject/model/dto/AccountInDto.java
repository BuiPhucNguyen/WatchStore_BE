package bpn.MockProject.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountInDto {
	private String firstName;
	private String lastName;
	private String email;
}
