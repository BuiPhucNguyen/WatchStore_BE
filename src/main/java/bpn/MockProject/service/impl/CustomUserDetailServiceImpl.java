package bpn.MockProject.service.impl;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bpn.MockProject.entity.Account;
import bpn.MockProject.model.CustomUserDetail;
import bpn.MockProject.repository.AccountRepository;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	public CustomUserDetailServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
//			throw new UsernameNotFoundException("User not found");
			return null;
		}
		return new CustomUserDetail(account.getUserName(), account.getPassword(), account.getRoles());
	}

}
