package bpn.MockProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.ResponseBuild;
import bpn.MockProject.model.ResponseModel;
import bpn.MockProject.model.dto.ChangePasswordDto;
import bpn.MockProject.model.dto.LoginDto;
import bpn.MockProject.model.dto.SignUpDto;
import bpn.MockProject.service.LoginService;

@RestController
@RequestMapping("/auths")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private ResponseBuild responseBuild;

	@PostMapping("/login")
	public ResponseModel login(@RequestBody LoginDto login) {
		ActionResult result = null;
		try {
			result = loginService.login(login);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}

	@PostMapping("/signup")
	public ResponseModel signUp(@RequestBody SignUpDto signup) {
		ActionResult result = null;
		try {
			result = loginService.signup(signup);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}
	
	@PutMapping("/change_password")
	public ResponseModel changePassword(@RequestBody ChangePasswordDto changePassword) {
		ActionResult result = null;
		try {
			result = loginService.changePassword(changePassword);
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
		}
		return responseBuild.build(result);
	}
}
