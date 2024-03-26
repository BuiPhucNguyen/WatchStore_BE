package bpn.MockProject.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bpn.MockProject.entity.Account;
import bpn.MockProject.entity.Role;
import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.ChangePasswordDto;
import bpn.MockProject.model.dto.LoginDto;
import bpn.MockProject.model.dto.SignUpDto;
import bpn.MockProject.model.entity.AccountModel;
import bpn.MockProject.repository.AccountRepository;
import bpn.MockProject.repository.RoleRepository;
import bpn.MockProject.service.LoginService;
import bpn.MockProject.utils.CurrentUserUtils;
import bpn.MockProject.utils.JwtUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailServiceImpl customUserDetailServiceImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ActionResult login(LoginDto login) {
		ActionResult result = new ActionResult();
		UserDetails userDetails = customUserDetailServiceImpl.loadUserByUsername(login.getUsername());

		if (userDetails == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USERNAME_OR_PASSWORD);
			return result;
		} else {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),
						login.getPassword(), userDetails.getAuthorities()));

//				SecurityContextHolder.getContext().setAuthentication(authentication);

				String token = JwtUtils.generateToken(userDetails.getUsername());

				result.setData(token);
				return result;
			} catch (Exception e) {
				result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USERNAME_OR_PASSWORD);
				return result;
			}
		}
	}

	@Override
	public ActionResult signup(SignUpDto signup) {
		ActionResult result = new ActionResult();

		if (accountRepository.findByUsername(signup.getUsername()) != null) {
			result.setErrorCodeEnum(ErrorCodeEnum.EXISTED_USERNAME_ACCOUNT);
			return result;
		}

		if (accountRepository.findByEmail(signup.getEmail()) != null) {
			result.setErrorCodeEnum(ErrorCodeEnum.EXISTED_EMAIL_ACCOUNT);
			return result;
		}

		Account account = new Account();

		account.setFirstName(signup.getFirstName());
		account.setLastName(signup.getLastName());
		account.setEmail(signup.getEmail());
		account.setUserName(signup.getUsername());
		account.setPassword(passwordEncoder.encode(signup.getPassword()));

		account.setStatus(true);
		account.setCreatedDate(new Date());

		Role role = roleRepository.getRoleByName("USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		account.setRoles(roles);

		Account accountResult = accountRepository.save(account);

		if (accountResult == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_CREATE);
			return result;
		}

		result.setData(AccountModel.transform(accountResult));
		return result;
	}

	@Override
	public ActionResult changePassword(ChangePasswordDto changePassword) {
		ActionResult result = new ActionResult();

		Account account = accountRepository.findByUsername(CurrentUserUtils.getCurrentUsernames());
		if (account == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
			return result;
		}

		if (!passwordEncoder.matches(changePassword.getOldPassword(), account.getPassword())) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_OLD_PASSWORD);
			return result;
		}

		account.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
		result.setData(new String("Change password success"));
		return result;
	}
}
