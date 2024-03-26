package bpn.MockProject.model.dto;

import java.util.List;

import bpn.MockProject.model.entity.AccountModel;
import lombok.Data;

@Data
public class AccountOutDto {
	private List<AccountModel> accounts;
	private Integer total;
}
