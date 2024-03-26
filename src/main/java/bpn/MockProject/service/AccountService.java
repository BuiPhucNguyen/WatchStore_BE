package bpn.MockProject.service;

import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.AccountInDto;

public interface AccountService {
	ActionResult getAccountsNotAdmin(Integer page, Integer size);
	
	ActionResult getAccountById(Integer id); 
	
	ActionResult getCurrentAccount();
	
	ActionResult updatetAccount(AccountInDto newAccount); 
	
	ActionResult ableAccount(Integer id); 
}
