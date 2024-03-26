package bpn.MockProject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import bpn.MockProject.entity.Account;
import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.AccountInDto;
import bpn.MockProject.model.dto.AccountOutDto;
import bpn.MockProject.model.entity.AccountModel;
import bpn.MockProject.repository.AccountRepository;
import bpn.MockProject.service.AccountService;
import bpn.MockProject.utils.CurrentUserUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ActionResult getAccountsNotAdmin(Integer page, Integer size) {
		ActionResult result = new ActionResult();

		Page<Account> pageResult = accountRepository.findAccountsNotAdmin(PageRequest.of(page - 1, size));

		if (pageResult.getNumberOfElements() == 0) {
			result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
			return result;
		}

		List<AccountModel> accountModels = pageResult.get().map(AccountModel::transform).collect(Collectors.toList());

		AccountOutDto outDto = new AccountOutDto();
		outDto.setAccounts(accountModels);
		outDto.setTotal(pageResult.getNumberOfElements());

		result.setData(outDto);

		return result;
	}

	@Override
	public ActionResult getAccountById(Integer id) {
		ActionResult result = new ActionResult();

		Account account = accountRepository.getAccountById(id);
		if (account == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
			return result;
		}
		result.setData(AccountModel.transform(account));
		return result;
	}

	@Override
	public ActionResult updatetAccount(AccountInDto newAccount) {
		ActionResult result = new ActionResult();

		Account accountTemp = accountRepository.findByUsername(CurrentUserUtils.getCurrentUsernames());

		if (accountTemp == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_UPDATE);
			return result;
		} else {
			accountTemp.setFirstName(newAccount.getFirstName());
			accountTemp.setLastName(newAccount.getLastName());

			if (accountRepository.findByEmail(newAccount.getEmail()) != null) {
				result.setErrorCodeEnum(ErrorCodeEnum.EXISTED_EMAIL_ACCOUNT);
				return result;
			} else {
				accountTemp.setEmail(newAccount.getEmail());
			}
		}

		Account accountUpdate = accountRepository.save(accountTemp);

		if (accountUpdate == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_UPDATE);
			return result;
		}
		result.setData(AccountModel.transform(accountUpdate));
		return result;
	}

	@Override
	public ActionResult ableAccount(Integer id) {

		ActionResult result = new ActionResult();

		Account account = accountRepository.getAccountById(id);
		if (account == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
			return result;
		}

		if (account.getStatus()) {
			account.setStatus(false);
			accountRepository.save(account);
			result.setData(new String("Disable account sucess!"));
		} else {
			account.setStatus(true);
			accountRepository.save(account);
			result.setData(new String("Enable account sucess!"));
		}

		return result;
	}

	@Override
	public ActionResult getCurrentAccount() {
		ActionResult result = new ActionResult();
		Account account = accountRepository.findByUsername(CurrentUserUtils.getCurrentUsernames());
		if (account == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
			return result;
		}
		result.setData(AccountModel.transform(account));
		return result;
	}
}
